import { Component, effect, inject, Input, input, signal } from '@angular/core';
import { TicketService } from '../../../ticket/data-access/ticket.service';
import { Ticket } from '../../../shared/interfaces/ticket';
import { __param } from 'tslib';
import { FormBuilder, ReactiveFormsModule, Validators } from '@angular/forms';
import { isRequired } from '../../utils/validators';
import { StorageTicketService } from '../../../shared/data-access/storage-ticket.service';
import { TableComponent } from '../../../ticket/ui/table/table.component';

@Component({
  selector: 'app-factura-form',
  imports: [ReactiveFormsModule, TableComponent],
  templateUrl: './factura-form.component.html',
  styleUrl: './factura-form.component.css',
})
export default class FacturaFormComponent {
  private _formBuilder = inject(FormBuilder);
  private _ticketService = inject(TicketService);

  tickets = signal<Ticket[]>([]);
  loading = this._ticketService.loading;

  ticketForm = this._formBuilder.group({
    rfc: this._formBuilder.control('', [
      Validators.required,
      Validators.pattern('^[A-Z]{4}[0-9]{6}[A-Z0-9]{3}$'),
    ]),
    nombre: this._formBuilder.control('', Validators.required),
    direccion: this._formBuilder.control('', Validators.required),
    colonia: this._formBuilder.control('', Validators.required),
    ciudad: this._formBuilder.control('', Validators.required),
    cp: this._formBuilder.control('', Validators.required),
    estado: this._formBuilder.control('', Validators.required),
    regimenFiscal: this._formBuilder.control('', Validators.required),
    usoCfdi: this._formBuilder.control('', Validators.required),
  });
  isRequired(field: 'folio' | 'codigoFacturacion' | 'sucursal') {
    return isRequired(field, this.ticketForm);
  }

  async onSubmit() {
    if (this.ticketForm.invalid) return;
  }

  constructor() {
    effect(() => {
      const allTickets = this._ticketService.getTickets();
      this.tickets.set(allTickets);
    });
  }
}
