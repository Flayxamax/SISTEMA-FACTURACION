import { inject, Injectable, signal } from '@angular/core';
import { BaseHttpService } from '../../shared/data-access/base-http.service';
import { catchError, Observable, tap, throwError } from 'rxjs';
import { Ticket } from '../../shared/interfaces/ticket';
import { StorageTicketService } from '../../shared/data-access/storage-ticket.service';
import { toast } from 'ngx-sonner';

export type TicketDTO = Omit<Ticket, 'id' | 'date' | 'total' | 'subTotal' | 'tipoPago' | 'factura' | 'productos'>;

@Injectable({
  providedIn: 'root',
})
export class TicketService extends BaseHttpService {
  private _storageTicket = inject(StorageTicketService);
  tickets = signal<Ticket[]>([]);
  ticketsDB = signal<Ticket[]>([]);
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

  loadTicketsDB(): void {
    this.loading.set(true);
    this._http
      .get<Ticket[]>(`${this._apiUrl}/ticket`)
      .pipe(
        tap((tickets) => {
          const extraerTickets = (entrada: any): any[] => {
            const resultado: any[] = [];
            for (const t of entrada) {
              if (typeof t === 'object' && t !== null && 'id' in t) {
                resultado.push(t);
                if (t.factura && typeof t.factura === 'object' && Array.isArray(t.factura.tickets)) {
                  const embebidos = t.factura.tickets.filter(
                    (tk: any) => typeof tk === 'object' && tk !== null && 'id' in tk
                  );
                  resultado.push(...extraerTickets(embebidos));
                }
              }
            }

            return resultado;
          };

          const ticketsFiltrados = extraerTickets(tickets);
          this.ticketsDB.set(ticketsFiltrados);
          this.loading.set(false);
        }),
        catchError((error) => {
          this.loading.set(false);
          toast.error(error || 'Error al cargar los tickets');
          return throwError(() => error);
        })
      )
      .subscribe();
  }

  generateTicket(): void {
    this.loading.set(true);
    this._http
      .post<Ticket>(`${this._apiUrl}/ticket/generar-ticket`, {})
      .pipe(
        tap(() => {
          this.loadTicketsDB();
        }),
        catchError((error) => {
          this.loading.set(false);
          toast.error(error);
          return throwError(() => error);
        })
      )
      .subscribe();
  }
}
