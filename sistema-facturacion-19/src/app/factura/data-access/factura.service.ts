import { Injectable } from '@angular/core';
import { Factura } from '../../shared/interfaces/factura';
import { map, Observable } from 'rxjs';
import { BaseHttpService } from '../../shared/data-access/base-http.service';

export type FacturaDTO = Omit<Factura, 'id' | 'fechaEmision' | 'fechaEmisionCertificado' | 'total' | 'subTotal'>;

interface TokenResponse {
  token: string;
}

@Injectable({
  providedIn: 'root',
})
export class FacturaService extends BaseHttpService {
  public saveFactura(factura: FacturaDTO): Observable<string> {
    return this._http
      .post<TokenResponse>(`${this._apiUrl}/factura`, factura)
      .pipe(map((response: TokenResponse) => response.token));
  }

  public getFacturaByToken(token: string): Observable<Factura> {
    return this._http.get<Factura>(`${this._apiUrl}/factura/${token}`);
  }
}
