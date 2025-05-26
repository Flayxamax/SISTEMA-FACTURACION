import { Component, effect, inject, signal } from '@angular/core';
import { TicketService } from '../../data-access/ticket.service';
import { DateformatPipe } from '../../ui/table/pipes/dateformat.pipe';
import { CurrencyPipe } from '@angular/common';
import { Ticket } from '../../../shared/interfaces/ticket';
import { toast } from 'ngx-sonner';
import { QRCodeComponent } from 'angularx-qrcode';

@Component({
  selector: 'app-generate-ticket',
  imports: [DateformatPipe, CurrencyPipe, QRCodeComponent],
  templateUrl: './generate-ticket.component.html',
  styleUrl: './generate-ticket.component.css',
})
export default class GenerateTicketComponent {
  private _ticketService = inject(TicketService);

  QrData: string = '';
  tickets = signal<Ticket[]>([]);
  loading = this._ticketService.loading;

  constructor() {
    effect(() => {
      this.tickets.set(this._ticketService.ticketsDB());
      console.log(this.tickets());
    });

    try {
      this._ticketService.loadTicketsDB();
    } catch (error: any) {
      toast.error(error?.message || String(error));
    }
  }

  generateQrData(ticket: Ticket) {
    return JSON.stringify({ folio: ticket.folio, codigoFacturacion: ticket.codigoFacturacion });
  }

  generateTicket() {
    try {
      this._ticketService.generateTicket();
      toast.success('Ticket generado correctamente');
    } catch (error: any) {
      toast.error(error);
    }
  }
}
