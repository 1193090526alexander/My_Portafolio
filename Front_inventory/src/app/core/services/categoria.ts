import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from '../../../environments/environment';

const base_url = environment.apiUrl;


@Injectable({
  providedIn: 'root',
})
export class CategoriaService {

  constructor(private http: HttpClient){

  }
  getObtenerCategorias(){
    const endpoint = `${base_url}/categories`;
    return this.http.get(endpoint)
  }
}
