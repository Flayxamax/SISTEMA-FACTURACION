import { Injectable } from '@angular/core';
import { BaseHttpService } from './base-http.service';
import { Observable } from 'rxjs';
import { Sucursal } from '../interfaces/sucursal';

@Injectable({
  providedIn: 'root',
})
export class SucursalService extends BaseHttpService {
  getAllSucursal(): Observable<Sucursal[]> {
    return this._http.get<Sucursal[]>(`${this._apiUrl}/sucursal`);
  }
}
