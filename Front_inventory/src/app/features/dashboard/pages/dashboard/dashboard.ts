import { Component, OnInit } from '@angular/core';
import { MaterialModule } from '../../../../shared/material/material';
import { ProductoService } from '../../../../core/services/product/product';
import { ProductElement } from '../../../produccion/pages/produccion-list/produccion-list';

// 1. IMPORTACIÓN CORRECTA DE CHART.JS: Esto registra de golpe todos los controladores (bar, line, etc.)
import Chart from 'chart.js/auto'; 

@Component({
  selector: 'app-dashboard',
  standalone: true,
  imports: [MaterialModule], // Eliminamos el "NgChartsModule" roto de aquí
  templateUrl: './dashboard.html',
  styleUrl: './dashboard.css',
})
export class Dashboard implements OnInit {

  chartBar: any;
  chardoughnut: any;

  constructor(private productoservice: ProductoService){}

  ngOnInit(): void {
    this.getProduct();
  }

  getProduct(): void {
    this.productoservice.getObtenerproductos().subscribe({
      next: (resp: any) => {
        console.log('RESPUESTA API:', resp);
        this.processProductResponse(resp);
      },
      error: (error: any) => console.error(error)
    });
  }
  
  processProductResponse(resp: any): void {
    const nameProduct: string[] = [];
    const quantity: number[] = [];
  
    if (resp && resp.metadata && resp.metadata[0]?.code === '00') {
      const listProduct = resp.producto?.productEntities || [];
  
      listProduct.forEach((element: ProductElement) => {
        nameProduct.push(element.name);
        quantity.push(element.quantity);
      });

      // 2. Destruir el gráfico anterior si ya existía para evitar duplicados en memoria
      if (this.chartBar) {
        this.chartBar.destroy();
      }

      // 3. Crear el gráfico de barras (ahora "bar" ya estará registrado gracias al import de arriba)
      this.chartBar = new Chart('canvas-bar', {
        type: 'bar',
        data: {
          labels: nameProduct,
          datasets: [
            {
              label: 'Cantidad de Productos', 
              data: quantity
            }
          ]
        },
        options: {
          responsive: true,
          maintainAspectRatio: false
        }
      });

      // 3. Crear el gráfico de barras (ahora "bar" ya estará registrado gracias al import de arriba)
      this.chardoughnut = new Chart('canvas-doughnut', {
        type: 'doughnut',
        data: {
          labels: nameProduct,
          datasets: [
            {
              label: 'Cantidad de Productos', 
              data: quantity
            }
          ]
        },
        options: {
          responsive: true,
          maintainAspectRatio: false
        }
      });
    }
  }
}