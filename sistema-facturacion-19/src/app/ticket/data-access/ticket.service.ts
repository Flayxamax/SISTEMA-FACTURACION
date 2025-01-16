import { inject, Injectable, signal } from '@angular/core';
import { BaseHttpService } from '../../shared/data-access/base-http.service';
import { catchError, Observable, tap, throwError } from 'rxjs';
import { Ticket } from '../../shared/interfaces/ticket';
import { StorageTicketService } from '../../shared/data-access/storage-ticket.service';

export type TicketDTO = Omit<Ticket, 'id' | 'date' | 'total' | 'subTotal' | 'tipoPago' | 'factura' | 'productos'>;

@Injectable({
  providedIn: 'root',
})
export class TicketService extends BaseHttpService {
  private _storageTicket = inject(StorageTicketService);
  tickets = signal<Ticket[]>([]);
  loading = signal<boolean>(true);

  getTicketById(id: number): Observable<Ticket> {
    return this._http.get<Ticket>(`${this._apiUrl}/ticket/${id}`);
  }

  getTicketByParams(folio: number, codigoFacturacion: number, sucursal_id: number): Observable<Ticket> {
    return this._http.get<Ticket>(
      `${this._apiUrl}/ticket/folio/${folio}/codigoFacturacion/${codigoFacturacion}/sucursal/${sucursal_id}`
    );
  }

  loadTickets() {
    this._storageTicket
      .loadTickets()
      .pipe(
        tap((tickets) => {
          this.tickets.set(tickets);
          this.loading.set(false);
        }),
        catchError((error) => {
          this.loading.set(false);
          return throwError(() => error);
        })
      )
      .subscribe();
  }

  addTicket(ticket: Ticket) {
    this.tickets.set([...this.tickets(), ticket]);
    this._storageTicket.saveTicket(this.tickets());
  }
}
