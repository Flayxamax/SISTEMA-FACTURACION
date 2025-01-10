import { inject, Injectable, signal } from '@angular/core';
import { BaseHttpService } from '../../shared/data-access/base-http.service';
import { catchError, Observable, tap, throwError } from 'rxjs';
import { Ticket } from '../../shared/interfaces/ticket';
import { StorageTicketService } from '../../shared/data-access/storage-ticket.service';
import { toSignal } from '@angular/core/rxjs-interop';

export type TicketDTO = Omit<
  Ticket,
  'id' | 'date' | 'total' | 'iva' | 'tipoPago' | 'factura' | 'productos'
>;

@Injectable({
  providedIn: 'root',
})
export class TicketService extends BaseHttpService {
  private _storageTicket = inject(StorageTicketService);
  loading = signal<boolean>(true);
  getTicketById(id: number): Observable<Ticket> {
    return this._http.get<Ticket>(`${this._apiUrl}/ticket/${id}`);
  }

  getTicketByParams(
    folio: number,
    codigoFacturacion: number,
    sucursal_id: number
  ): Observable<Ticket> {
    return this._http.get<Ticket>(
      `${this._apiUrl}/ticket/folio/${folio}/codigoFacturacion/${codigoFacturacion}/sucursal/${sucursal_id}`
    );
  }

  getTickets = toSignal(
    this._storageTicket.loadTickets().pipe(
      tap(() => {
        this.loading.set(false);
      }),
      catchError((error) => {
        this.loading.set(false);
        return throwError(() => error);
      })
    ),
    {
      initialValue: [],
    }
  );
}
