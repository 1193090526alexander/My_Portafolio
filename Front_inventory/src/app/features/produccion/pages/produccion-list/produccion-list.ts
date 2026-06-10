import { Component, inject, OnInit, ViewChild } from '@angular/core';
import { RouterLink } from '@angular/router';
import { MaterialModule } from '../../../../shared/material/material';
import { ConfirmDialog } from '../../../../shared/components/confirm-dialog/confirm-dialog';
import { MatPaginator } from '@angular/material/paginator';
import { MatTableDataSource } from '@angular/material/table';
import { ProductoService } from '../../../../core/services/product/product';
import { ProduccionCreate } from '../produccion-create/produccion-create';
import { MatSnackBar, MatSnackBarRef, SimpleSnackBar } from '@angular/material/snack-bar';
import { MatDialog } from '@angular/material/dialog';
import { Util } from '../../../../core/services/util/util';


@Component({
  selector: 'app-produccion-list',
  imports: [RouterLink, MaterialModule, ConfirmDialog],
  templateUrl: './produccion-list.html',
  styleUrl: './produccion-list.css',
})
export class ProduccionList implements OnInit{

  isAdmin: any;

  private productoservice =  inject(ProductoService);
  private dialog = inject(MatDialog);
  constructor(private snackBar: MatSnackBar,
    private util: Util
  ){}
  
  ngOnInit(): void {
    this.getProduct();
    this.isAdmin =  this.util.isAdmin();
  }

  displayedColumns: string[] = ['id', 'name', 'price', 'category', 'picture', 'quantity', 'acciones'];
  dataSource = new MatTableDataSource<ProductElement>();


   @ViewChild(MatPaginator)
  paginator!: MatPaginator
  openDialog(): void {
      const dialogRef = this.dialog.open(ProduccionCreate, {
        width: '450px',
        disableClose: true
      });
  
      dialogRef.afterClosed().subscribe(result => {
        if (result === true) {
          this.getProduct();
        }
      });
    }
   getProduct(): void {
  this.productoservice.getObtenerproductos().subscribe({
    next: (resp: any) => {
      console.log('RESPUESTA API:', resp);
      this.processProductResponse(resp);
    },
    error: (error: any) => console.log(error)
  });
}

 processProductResponse(resp: any): void {
  const dataprodcut: ProductElement[] = [];

  // 1. Añadimos validación segura para evitar errores de "undefined" si la API falla
  if (resp && resp.metadata && resp.metadata[0]?.code === '00') {
    
    // Acceso seguro al arreglo de productos
    const listProduct = resp.producto?.productEntities || [];

    listProduct.forEach((element: ProductElement) => {
      
      if (element.picture) {
        // 2. Limpiamos espacios en blanco o saltos de línea invisibles que puedan alterar el string
        const cleanPicture = String(element.picture).trim();

        if (cleanPicture.startsWith('data:image')) {
          // Si por alguna razón ya tiene el prefijo o la URL local, la dejamos intacta
          element.picture = cleanPicture;
        } else if (cleanPicture === 'null' || cleanPicture === '') {
          // Si el backend manda el string "null" de texto, lo tratamos como vacío
          element.picture = '';
        } else {
          // Si es el Base64 puro y limpio de la BD, le ponemos el prefijo una única vez
          element.picture = 'data:image/jpeg;base64,' + cleanPicture;
        }
      } else {
        // 3. Si no trae foto (null/undefined), le asignamos un string vacío
        // Esto evita que el HTML intente romper los headers del navegador
        element.picture = '';
      }
      
      dataprodcut.push(element);
    });

    this.dataSource.data = dataprodcut;
    
    // 4. Forzamos la asignación del paginador por si se pierde al filtrar en la búsqueda
    if (this.paginator) {
      this.dataSource.paginator = this.paginator;
    }
  }
}

    openProdcutDialog(){
    const dialogRef = this.dialog.open(ProduccionCreate,{
      width: '450px'
    });
    dialogRef.afterClosed().subscribe((result:any) =>{
      if(result == 1){
        this.openSnackBar("prodcuto agregad", "Exitosa");
        this.getProduct();
      }else if (result == 2){
        this.openSnackBar("Se produjo un error al guardar el producto", "Error");
      }
    })
  }

  openSnackBar(message: string, action: string): MatSnackBarRef<SimpleSnackBar>{
    return this.snackBar.open(message, action, {
      duration: 2000
    })
  }

edit(id: number, name: string, category: any, price: number, quantity: number, picture: any): void { // <-- Agregamos ', picture: any'
  
  console.log("Datos enviados al diálogo:", { id, name, category, price, quantity, picture });

  const dialogRef = this.dialog.open(ProduccionCreate, {
    width: '450px',
    disableClose: true, // Evita que se cierre al hacer clic afuera
    data: {
      id: id, 
      name: name, 
      price: price,
      quantity: quantity,
      category: category,
      picture: picture // <-- Esto es vital para que ProduccionCreate no rompa
    }
  });

  dialogRef.afterClosed().subscribe((result: any) => {
    if (result == 1) {
      this.openSnackBar("Producto actualizado", "Exitosa");
      this.getProduct();
    } else if (result == 2) {
      this.openSnackBar("Se produjo un error al actualizar el producto", "Error");
    }
  });
}


delete(id: any) {
    console.log('ID A ELIMINAR:', id);
    
    // 1. Abrimos el diálogo pasándole textos personalizados para PRODUCTOS
    const dialogRef = this.dialog.open(ConfirmDialog, {
      width: '400px',
      data: {
        titulo: 'Eliminar Producto',
        mensaje: '¿Está seguro de que desea eliminar este producto? Esta acción no se puede deshacer.'
      }
    });

    // 2. Escuchamos la respuesta booleana
    dialogRef.afterClosed().subscribe((confirmado: boolean) => {
      if (confirmado === true) {
        
        // 3. Ejecutamos la eliminación llamando al servicio de productos
        this.productoservice.deleteProduct(id).subscribe({
          next: () => {
            this.openSnackBar('Producto eliminado con éxito', 'Exitosa');
            this.getProduct(); // Refrescamos la tabla
          },
          error: (err) => {
            console.error(err);
            this.openSnackBar('Error al eliminar el producto', 'Error');
          }
        });

      }
    });
  }

  buscarProduct(event: Event): void {

  const name = (event.target as HTMLInputElement).value.trim();

  if (name.length === 0) {
    this.getProduct();
    return;
  }

  this.productoservice.getProductByName(name).subscribe({
    next: (response: any) => {
      this.dataSource.data = response.producto.productEntities;
    },
    error: (error: any) => {
      console.log('Error buscando product:', error);
    }
  });
}

}


export interface ProductElement {
  id: number;
  name: string;
  price: number;
  category: any;
  picture: any;
  quantity: number;
}