import { Injectable } from '@angular/core';
import { BaseHttpService } from './base-http.service';
import { Observable } from 'rxjs';
import { UsoCfdi } from '../interfaces/uso-cfdi';

@Injectable({
  providedIn: 'root',
})
export class UsoCfdiService extends BaseHttpService {
  getAllUsoCfdi(): Observable<UsoCfdi[]> {
    return this._http.get<UsoCfdi[]>(`${this._apiUrl}/usocfdi`);
  }
}
