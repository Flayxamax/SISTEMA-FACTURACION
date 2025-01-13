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

@Component({
  selector: 'app-ticket-form',
  imports: [ReactiveFormsModule],
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
        } else {
          toast.error('Ticket no encontrado');
        }
      },
      (error) => {
        console.clear();
        toast.error('Error al buscar el ticket');
      }
    );
  }
}
