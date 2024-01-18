import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { TauxEndPoint } from '../constant/taux';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class TauxService {
  constructor(private _http: HttpClient) {}
  addCategory(data: any): Observable<any> {
    return this._http.post(TauxEndPoint.API_ENDPOINT + 'taux', data);
  }
}
