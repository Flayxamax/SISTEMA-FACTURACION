import { Component, effect, inject, Input, input, signal } from '@angular/core';
import {
  TicketDTO,
  TicketService,
} from '../../../ticket/data-access/ticket.service';
import { Ticket } from '../../../shared/interfaces/ticket';
import { FormBuilder, ReactiveFormsModule, Validators } from '@angular/forms';
import { isRequired } from '../../utils/validators';
import { StorageTicketService } from '../../../shared/data-access/storage-ticket.service';
import { TableComponent } from '../../../ticket/ui/table/table.component';
import { SucursalService } from '../../../shared/data-access/sucursal.service';
import { Sucursal } from '../../../shared/interfaces/sucursal';
import { toast } from 'ngx-sonner';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';
import { AddAnotherTicketFormComponent } from '../../../ticket/features/add-another-ticket-form/add-another-ticket-form.component';

@Component({
  selector: 'app-factura-form',
  imports: [
    ReactiveFormsModule,
    TableComponent,
    CommonModule,
    AddAnotherTicketFormComponent,
  ],
  templateUrl: './factura-form.component.html',
  styleUrl: './factura-form.component.css',
})
export default class FacturaFormComponent {
  private _formBuilder = inject(FormBuilder);
  private _ticketService = inject(TicketService);
  private _router = inject(Router);
  private _storageTicket = inject(StorageTicketService);

  tickets = signal<Ticket[]>([]);
  loading = this._ticketService.loading;

  facturaForm = this._formBuilder.group({
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

  async onSubmit() {
    if (this.facturaForm.invalid) return;
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
  }
}
