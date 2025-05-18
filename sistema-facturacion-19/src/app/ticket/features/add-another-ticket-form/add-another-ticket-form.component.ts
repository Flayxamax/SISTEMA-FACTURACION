import { Component, effect, inject, signal } from '@angular/core';
import { FormBuilder, ReactiveFormsModule, Validators } from '@angular/forms';
import { toast } from 'ngx-sonner';
import { StorageTicketService } from '../../../shared/data-access/storage-ticket.service';
import { SucursalService } from '../../../shared/data-access/sucursal.service';
import { Sucursal } from '../../../shared/interfaces/sucursal';
import { Ticket } from '../../../shared/interfaces/ticket';
import { TicketDTO, TicketService } from '../../data-access/ticket.service';
import { isRequired } from '../../utils/validators';
import { CommonModule } from '@angular/common';
import { ZXingScannerModule } from '@zxing/ngx-scanner';
import { BarcodeFormat } from '@zxing/library';

@Component({
  selector: 'app-add-another-ticket-form',
  imports: [ReactiveFormsModule, CommonModule, ZXingScannerModule],
  templateUrl: './add-another-ticket-form.component.html',
  styleUrl: './add-another-ticket-form.component.css',
})
export class AddAnotherTicketFormComponent {
  private _sucursalService = inject(SucursalService);
  private _storageTicket = inject(StorageTicketService);
  private _ticketService = inject(TicketService);
  private _formBuilder = inject(FormBuilder);

  showScanner = false;

  allowedFormats = [BarcodeFormat.QR_CODE];
  scannedResult: string | null = null;
  hasDevices = false;
  availableDevices: MediaDeviceInfo[] = [];
  selectedDevice: MediaDeviceInfo | undefined; // Fix: Use `undefined` instead of `null`

  onCodeResult(result: string) {
    try {
      const parsedJSONResult = JSON.parse(result);
      this.scannedResult = result;

      const folio = parsedJSONResult.folio;
      const codigoFacturacion = parsedJSONResult.codigoFacturacion;

      if (folio && codigoFacturacion) {
        this.ticketForm.patchValue({
          folio: folio,
          codigoFacturacion: codigoFacturacion,
        });
        toast.success('Datos escaneados correctamente');
        this.showScanner = false;
      } else {
        toast.error('QR no contiene datos válidos');
      }
    } catch (error) {
      console.error('Error parsing QR result:', error);
      toast.error('Error al leer el código QR');
    }
  }

  onDeviceSelectChange(event: Event) {
    const target = event.target as HTMLSelectElement; // Fix: Properly typecast EventTarget
    this.selectedDevice = this.availableDevices.find((device) => device.deviceId === target.value);
  }

  onHasDevices(hasDevices: boolean) {
    this.hasDevices = hasDevices;
  }

  onDevicesFound(devices: MediaDeviceInfo[]) {
    this.availableDevices = devices;
    if (devices.length > 0) {
      this.selectedDevice = devices[0]; // Fix: Ensure a device is selected initially
    }
  }

  onError(error: any) {
    console.error('Barcode scanning error:', error);
  }

  openScanner() {
    this.showScanner = true;
  }

  tickets = signal<Ticket[]>([]);
  loading = this._ticketService.loading;
  isVisible = false;

  toggleVisibility() {
    this.isVisible = !this.isVisible;
  }
  sucursales: Sucursal[] = [];
  ticketForm = this._formBuilder.group({
    folio: this._formBuilder.control('', [Validators.required, Validators.pattern('^[0-9]*$')]),
    codigoFacturacion: this._formBuilder.control('', [Validators.required, Validators.pattern('^[0-9]*$')]),
    sucursal: this._formBuilder.control('', Validators.required),
  });

  isRequired(field: 'folio' | 'codigoFacturacion' | 'sucursal') {
    return isRequired(field, this.ticketForm);
  }

  async onSubmitTicket() {
    if (this.ticketForm.invalid) return;

    const { folio, codigoFacturacion, sucursal } = this.ticketForm.value;
    const ticket: TicketDTO = {
      folio: Number(folio) || 0,
      codigoFacturacion: Number(codigoFacturacion) || 0,
      sucursal: { id: Number(sucursal) || 0 } as Sucursal,
    };

    this._ticketService.getTicketByParams(ticket.folio, ticket.codigoFacturacion, ticket.sucursal.id).subscribe(
      (foundTicket: Ticket) => {
        if (foundTicket) {
          const isDuplicate = this.tickets().some((t) => t.id === foundTicket.id);
          if (isDuplicate) {
            toast.error('El ticket ya ha sido agregado');
          } else {
            toast.success('Ticket Agregado');
            this._storageTicket.saveTicket([foundTicket]);
            this._ticketService.addTicket(foundTicket);
            this.ticketForm.setValue({ folio: '', codigoFacturacion: '', sucursal: '' });
          }
        }
      },
      (error) => {
        console.clear();
        toast.error(error);
      }
    );
  }

  constructor() {
    effect(() => {
      if (!this._ticketService.loading()) {
        const allTickets = this._ticketService.tickets();
        this.tickets.set(allTickets);
      }
    });

    this._sucursalService.getAllSucursal().subscribe(
      (sucursales) => {
        this.sucursales = sucursales;
      },
      (error) => {
        toast.error(error);
      }
    );
    this._ticketService.loadTickets();
  }
}
