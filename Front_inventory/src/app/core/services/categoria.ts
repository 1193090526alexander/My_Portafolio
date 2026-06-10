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



  saveCategoria(body: any){
    const endpoint = `${base_url}/categories`;
    return this.http.post(endpoint, body);

  }


  updateCategoria(body: any, idcategory: any){
    const endpoint = `${base_url}/categories/${idcategory}`
    return this.http.put(endpoint, body);
  }

  deleteCategoria(idcategory: any){
    const endpoint = `${base_url}/categories/${idcategory}`
    return this.http.delete(endpoint);
  }


getcategoriasByName(name: any){
  const endpoint = `${base_url}/categories/name/${name}`
    return this.http.get(endpoint);
}

exportCategories(){
  const endpoint =  `${base_url}/categories/export/excel`
    return this.http.get(endpoint,{
      responseType: 'blob'
    });
  
}

}
