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

@Component({
  selector: 'app-add-another-ticket-form',
  imports: [ReactiveFormsModule, CommonModule],
  templateUrl: './add-another-ticket-form.component.html',
  styleUrl: './add-another-ticket-form.component.css',
})
export class AddAnotherTicketFormComponent {
  private _sucursalService = inject(SucursalService);
  private _storageTicket = inject(StorageTicketService);
  private _ticketService = inject(TicketService);
  private _formBuilder = inject(FormBuilder);

  tickets = signal<Ticket[]>([]);
  loading = this._ticketService.loading;
  isVisible = false;

  toggleVisibility() {
    this.isVisible = !this.isVisible;
  }
  sucursales: Sucursal[] = [];
  ticketForm = this._formBuilder.group({
    folio: this._formBuilder.control('', [
      Validators.required,
      Validators.pattern('^[0-9]*$'),
    ]),
    codigoFacturacion: this._formBuilder.control('', [
      Validators.required,
      Validators.pattern('^[0-9]*$'),
    ]),
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

    this._ticketService
      .getTicketByParams(
        ticket.folio,
        ticket.codigoFacturacion,
        ticket.sucursal.id
      )
      .subscribe(
        (foundTicket: Ticket) => {
          if (foundTicket) {
            const isDuplicate = this.tickets().some(
              (t) => t.id === foundTicket.id
            );
            if (isDuplicate) {
              toast.error('El ticket ya ha sido agregado');
            } else {
              toast.success('Ticket Agregado');
              this._storageTicket.saveTicket([foundTicket]);
              this._ticketService.addTicket(foundTicket);
            }
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
        console.error(error);
      }
    );
    this._ticketService.loadTickets();
  }
}
