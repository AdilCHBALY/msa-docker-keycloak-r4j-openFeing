import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {forkJoin, from, map, Observable, of, switchMap} from "rxjs";
import {TerrainEndPoint} from "../constant/terrain";
import {KeycloakService} from "keycloak-angular";
import {KeycloakProfile} from "keycloak-js";
import {TaxeEndPoint} from "../constant/taxe";
import {CategoryEndPoint} from "../constant/category";

@Injectable({
  providedIn: 'root'
})
export class TerrainService {

  profile?:KeycloakProfile

  constructor(private _http:HttpClient,private keycloakService:KeycloakService) { }

  getTerrainForTaxes():Observable<any>{
    return this._http.get(TerrainEndPoint.API_ENDPOINT+'terrain')
  }


  pay(id:number):Observable<any>{
    return this._http.get(TaxeEndPoint.API_ENDPOINT+'taxe/pay/'+id)
  }
  addTerrain(data:any):Observable<any>{
    return this._http.post(TerrainEndPoint.API_ENDPOINT + 'terrain', data);
  }

  deleteTerrain(id:number):Observable<any>{
    return this._http.delete(TerrainEndPoint.API_ENDPOINT+'terrain/'+id)
  }

  getAll():Observable<any>{
    return this._http.get<any[]>(TerrainEndPoint.API_ENDPOINT+'terrain').pipe(
      map(terrains => {
        const taxesObservables = terrains.map(terrain =>
          this._http.get<any[]>(`http://192.168.13.1:8888/TAXE-SERVICE/api/taxe/terrain/${terrain.id}`).pipe(
            map(taxes => ({ ...terrain, taxes }))
          )
        );
        return forkJoin(taxesObservables);
      }),
      switchMap(observables => forkJoin(observables))
    );
  }

  getByClient():Observable<any>{
    if (this.keycloakService.isLoggedIn()) {
      return from(this.keycloakService.loadUserProfile()).pipe(
        switchMap(profile => {
          if (profile && profile.id) {
            return this._http.get<any[]>(`http://localhost:8888/TERRAIN-SERVICE/api/terrain/client/${profile.id}`).pipe(
              switchMap(terrains => {
                const taxesObservables = terrains.map(terrain =>
                  this._http.get<any[]>(`http://192.168.13.1:8888/TAXE-SERVICE/api/taxe/terrain/${terrain.id}`).pipe(
                    map(taxes => ({ ...terrain, taxes }))
                  )
                );
                return forkJoin(taxesObservables);
              })
            );
          } else {
            return of([]);
          }
        })
      );
    } else {
      return of([]);
    }
  }
}
