import { Injectable } from '@angular/core';
import { BaseHttpService } from './base-http.service';
import { Observable } from 'rxjs';
import { Estado } from '../interfaces/estado';

@Injectable({
  providedIn: 'root',
})
export class EstadoService extends BaseHttpService {
  getAllEstado(): Observable<Estado[]> {
    return this._http.get<Estado[]>(`${this._apiUrl}/estado`);
  }
}
