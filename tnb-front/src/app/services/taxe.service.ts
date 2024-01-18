import {HttpClient, HttpHeaders} from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { TaxeEndPoint } from '../constant/taxe';

@Injectable({
  providedIn: 'root',
})
export class TaxeService {
  constructor(private _http: HttpClient) {}

  getAll(): Observable<any> {
    return this._http.get(TaxeEndPoint.API_ENDPOINT + 'taxe');
  }

  getTaxeByTerrain(id:number):Observable<any>{
    return this._http.get(TaxeEndPoint.API_ENDPOINT+'taxe/terrain/'+id)
  }

  updateTaxe(id: number, data: any): Observable<any> {
    return this._http.put(
      TaxeEndPoint.API_ENDPOINT + 'taxe/' + id,
      data
    );
  }
  addTaxe(data:any):Observable<any>{
    return this._http.post(TaxeEndPoint.API_ENDPOINT+'taxe',data)
  }

  deleteTaxe(id: number): Observable<any> {
    return this._http.delete(TaxeEndPoint.API_ENDPOINT + 'taxe/' + id);
  }
}
