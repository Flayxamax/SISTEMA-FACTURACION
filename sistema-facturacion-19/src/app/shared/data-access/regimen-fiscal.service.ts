import { Injectable } from '@angular/core';
import { BaseHttpService } from './base-http.service';
import { Observable } from 'rxjs';
import { Regimen } from '../interfaces/regimen';

@Injectable({
  providedIn: 'root',
})
export class RegimenFiscalService extends BaseHttpService {
  getAllRegimenFiscal(): Observable<Regimen[]> {
    return this._http.get<Regimen[]>(`${this._apiUrl}/regimen`);
  }
}
