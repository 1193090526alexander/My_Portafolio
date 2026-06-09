import { Injectable } from '@angular/core';
import { environment } from '../../../../environments/environment';
import { HttpClient } from '@angular/common/http';


const base_url = environment.apiUrl;
@Injectable({
  providedIn: 'root',
})
export class ProductoService {

  constructor(private http: HttpClient){

  }

  getObtenerproductos(){
    const endpoint = `${base_url}/product`;
    return this.http.get(endpoint)
  }

  saveProduct(body: any){
    const endpoint = `${base_url}/products`;
    return this.http.post(endpoint, body);

  }

  updateProdcut(body: any, id: any){
    const endpoint = `${base_url}/products/${id}`
    return this.http.put(endpoint, body);
  }

  deleteProduct(id: any){
    const endpoint = `${base_url}/product/${id}`
    return this.http.delete(endpoint);
  }


  getProductByName(name: any){
  const endpoint = `${base_url}/product/name/${name}`
    return this.http.get(endpoint);
}
}
