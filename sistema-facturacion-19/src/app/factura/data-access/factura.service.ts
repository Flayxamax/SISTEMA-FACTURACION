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

  public getFacturaByTicket(folio: string, codigoFacturacion: string): Observable<string> {
    return this._http
      .get<TokenResponse>(`${this._apiUrl}/factura/factura-search/${folio}/${codigoFacturacion}`)
      .pipe(map((response: TokenResponse) => response.token));
  }

  public downloadFacturaXml(token: string): Observable<{ blob: Blob; filename: string }> {
    return this._http
      .get(`${this._apiUrl}/factura/download-xml/${token}`, {
        observe: 'response',
        responseType: 'blob',
      })
      .pipe(
        map((response) => {
          const contentDisposition = response.headers.get('Content-Disposition');
          let filename = 'factura.xml';

          if (contentDisposition) {
            const match = contentDisposition.match(/filename="?(.*?)"?$/);
            if (match && match[1]) {
              filename = match[1];
            }
          }

          return { blob: response.body!, filename };
        })
      );
  }
}
