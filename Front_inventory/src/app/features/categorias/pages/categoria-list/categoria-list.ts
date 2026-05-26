import { Component, inject, OnInit } from '@angular/core';
import { RouterLink } from '@angular/router';
import { CategoriaService } from '../../../../core/services/categoria';
import { error } from 'console';

@Component({
  selector: 'app-categoria-list',
  imports: [RouterLink],
  templateUrl: './categoria-list.html',
  styleUrl: './categoria-list.css',
})

export class CategoriaList implements OnInit{

    private categoriaServices = inject(CategoriaService);

  ngOnInit(): void {
    this.getCategorias();
  }


  getCategorias(): void {

    this.categoriaServices.getObtenerCategorias().subscribe((data:any)=>{

      console.log("Respuesta categorias: ", data)
    }, (error: any)=>{
      console.log("error", error)
    })
  }
}
