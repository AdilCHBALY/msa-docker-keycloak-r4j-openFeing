import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { CategoryEndPoint } from '../constant/category';

@Injectable({
  providedIn: 'root',
})
export class CategoryService {
  constructor(private _http: HttpClient) {}
  getAllCategories(): Observable<any> {
    return this._http.get(CategoryEndPoint.API_ENDPOINT + 'category');
  }

  addCategory(data: any): Observable<any> {
    return this._http.post(CategoryEndPoint.API_ENDPOINT + 'category', data);
  }

  updateCategory(id: number, data: any): Observable<any> {
    return this._http.put(
      CategoryEndPoint.API_ENDPOINT + 'category/' + id,
      data
    );
  }

  deleteCategory(id: number): Observable<any> {
    return this._http.delete(CategoryEndPoint.API_ENDPOINT + 'category/' + id);
  }
}
