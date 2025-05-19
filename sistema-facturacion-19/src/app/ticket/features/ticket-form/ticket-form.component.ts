import { Component, inject } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { SucursalService } from '../../../shared/data-access/sucursal.service';
import { Sucursal } from '../../../shared/interfaces/sucursal';
import { TicketDTO, TicketService } from '../../data-access/ticket.service';
import { toast } from 'ngx-sonner';
import { isRequired } from '../../utils/validators';
import { Router } from '@angular/router';
import { Ticket } from '../../../shared/interfaces/ticket';
import { StorageTicketService } from '../../../shared/data-access/storage-ticket.service';
import { ZXingScannerModule } from '@zxing/ngx-scanner';
import { BarcodeFormat } from '@zxing/library';

@Component({
  selector: 'app-ticket-form',
  imports: [ReactiveFormsModule, ZXingScannerModule],
  templateUrl: './ticket-form.component.html',
  styleUrl: './ticket-form.component.css',
})
export default class TicketFormComponent {
  private _formBuilder = inject(FormBuilder);
  private _sucursalService = inject(SucursalService);
  private _ticketService = inject(TicketService);
  private _router = inject(Router);
  private _storageTicket = inject(StorageTicketService);

  isRequired(field: 'folio' | 'codigoFacturacion' | 'sucursal') {
    return isRequired(field, this.ticketForm);
  }

  sucursales: Sucursal[] = [];

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

  ticketForm = this._formBuilder.group({
    folio: this._formBuilder.control('', [Validators.required, Validators.pattern('^[0-9]*$')]),
    codigoFacturacion: this._formBuilder.control('', [Validators.required, Validators.pattern('^[0-9]*$')]),
    sucursal: this._formBuilder.control('', Validators.required),
  });

  constructor() {
    this._storageTicket.deleteTickets();
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

  async onSubmit() {
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
          toast.success('Ticket encontrado');
          this._ticketService.addTicket(foundTicket);
          this._router.navigate(['ticket/facturacion']);
        }
      },
      (error) => {
        console.clear();
        toast.error(error);
      }
    );
  }
}
