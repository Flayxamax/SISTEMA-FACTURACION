import { Component, effect, inject, signal } from '@angular/core';
import { TicketService } from '../../../ticket/data-access/ticket.service';
import { Ticket } from '../../../shared/interfaces/ticket';
import { FormBuilder, ReactiveFormsModule, Validators } from '@angular/forms';
import { isRequired } from '../../utils/validators';
import { StorageTicketService } from '../../../shared/data-access/storage-ticket.service';
import { TableComponent } from '../../../ticket/ui/table/table.component';
import { toast } from 'ngx-sonner';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';
import { AddAnotherTicketFormComponent } from '../../../ticket/features/add-another-ticket-form/add-another-ticket-form.component';
import { EstadoService } from '../../../shared/data-access/estado.service';
import { RegimenFiscalService } from '../../../shared/data-access/regimen-fiscal.service';
import { UsoCfdiService } from '../../../shared/data-access/uso-cfdi.service';
import { Estado } from '../../../shared/interfaces/estado';
import { Regimen } from '../../../shared/interfaces/regimen';
import { UsoCfdi } from '../../../shared/interfaces/uso-cfdi';
import { FacturaDTO, FacturaService } from '../../data-access/factura.service';
import { lastValueFrom } from 'rxjs';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-factura-form',
  imports: [ReactiveFormsModule, TableComponent, CommonModule, AddAnotherTicketFormComponent],
  templateUrl: './factura-form.component.html',
  styleUrl: './factura-form.component.css',
})
export default class FacturaFormComponent {
  private _formBuilder = inject(FormBuilder);
  private _ticketService = inject(TicketService);
  private _router = inject(Router);
  private _storageTicket = inject(StorageTicketService);
  private _estadoService = inject(EstadoService);
  private _regimenFiscalService = inject(RegimenFiscalService);
  private _usoCfdiService = inject(UsoCfdiService);
  private _facturaService = inject(FacturaService);

  transformToUpperCase(event: Event): void {
    const input = event.target as HTMLInputElement;

    input.value = input.value.toUpperCase();
  }

  estados: Estado[] = [];
  regimenFiscales: Regimen[] = [];
  usosCfdi: UsoCfdi[] = [];

  tickets = signal<Ticket[]>([]);
  loading = this._ticketService.loading;

  isRequired(
    field: 'rfc' | 'nombre' | 'direccion' | 'colonia' | 'ciudad' | 'cp' | 'estado' | 'regimenFiscal' | 'usoCfdi'
  ) {
    return isRequired(field, this.facturaForm);
  }

  facturaForm = this._formBuilder.group({
    rfc: this._formBuilder.control('', Validators.required),
    nombre: this._formBuilder.control('', Validators.required),
    direccion: this._formBuilder.control('', Validators.required),
    colonia: this._formBuilder.control('', Validators.required),
    ciudad: this._formBuilder.control('', Validators.required),
    cp: this._formBuilder.control('', Validators.required),
    estado: this._formBuilder.control('', Validators.required),
    regimenFiscal: this._formBuilder.control('', Validators.required),
    usoCfdi: this._formBuilder.control('', Validators.required),
  });

  async onSubmit() {
    if (this.facturaForm.invalid) return;

    const result = await Swal.fire({
      title: '¿Estás seguro de generar la factura?',
      html: '<span style="color: red;"><strong>UNA VEZ GENERADA LA FACTURA NO SE PODRÁ MODIFICAR.</strong></span>',
      icon: 'warning',
      showDenyButton: true,
      confirmButtonColor: '#28a745',
      confirmButtonText: `Generar factura`,
      denyButtonText: `No`,
      reverseButtons: true,
    });

    if (!result.isConfirmed) return;

    try {
      const { rfc, nombre, direccion, colonia, ciudad, cp, estado, regimenFiscal, usoCfdi } = this.facturaForm.value;
      const selectedEstado = this.estados.find((e) => e.id === Number(estado))?.nombre || '';

      const factura: FacturaDTO = {
        rfc: rfc?.toUpperCase() || '',
        nombre: nombre || '',
        direccion: direccion || '',
        colonia: colonia || '',
        ciudad: ciudad || '',
        codigoPostal: cp || '',
        estado: selectedEstado || '',
        regimenFiscal: { id: Number(regimenFiscal) || 0 } as Regimen,
        usoCFDI: { id: Number(usoCfdi) || 0 } as UsoCfdi,
        tickets: this.tickets(),
      };

      const token: string = await lastValueFrom(this._facturaService.saveFactura(factura));

      toast.success('Factura generada');
      this._storageTicket.deleteTickets();
      this._ticketService.loadTickets();
      this._router.navigate(['ticket/facturacion/', token]);
    } catch (error: any) {
      toast.error(error);
    }
  }

  private getDataSelect(): void {
    this._estadoService.getAllEstado().subscribe(
      (estados: Estado[]) => {
        this.estados = estados;
      },
      (error: any) => {
        toast.error(error);
      }
    );

    this._regimenFiscalService.getAllRegimenFiscal().subscribe(
      (regimenFiscales) => {
        this.regimenFiscales = regimenFiscales;
      },
      (error) => {
        toast.error(error);
      }
    );

    this._usoCfdiService.getAllUsoCfdi().subscribe(
      (usosCfdi) => {
        this.usosCfdi = usosCfdi;
      },
      (error) => {
        toast.error(error);
      }
    );
  }

  constructor() {
    this._storageTicket.loadTickets().subscribe((storedTickets) => {
      if (!storedTickets || storedTickets.length === 0) {
        this._router.navigate(['ticket']);
        return;
      }
    });

    effect(() => {
      if (!this._ticketService.loading()) {
        const allTickets = this._ticketService.tickets();
        this.tickets.set(allTickets);
      }
    });
    this._ticketService.loadTickets();
    this.getDataSelect();
  }
}
