import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { environment } from '../../../environments/environment.development';

@Injectable({
  providedIn: 'root',
})
export class BaseHttpService {
  _http = inject(HttpClient);
  _apiUrl = environment.API_URL;
}
