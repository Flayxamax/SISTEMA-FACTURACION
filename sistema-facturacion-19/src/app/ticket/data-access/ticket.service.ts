import { Injectable } from '@angular/core';
import { BaseHttpService } from '../../shared/data-access/base-http.service';
import { Observable } from 'rxjs';
import { Ticket } from '../../shared/interfaces/ticket';

export type TicketDTO = Omit<Ticket, 'id' | 'date' | 'total'>;

@Injectable({
  providedIn: 'root',
})
export class TicketService extends BaseHttpService {
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
}
