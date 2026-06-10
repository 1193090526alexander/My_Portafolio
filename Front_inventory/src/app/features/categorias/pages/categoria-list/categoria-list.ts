import { Component, inject, OnInit, ViewChild } from '@angular/core';
import { RouterLink } from '@angular/router';
import { MatTableDataSource } from '@angular/material/table';
import { MatDialog } from '@angular/material/dialog';
import { ConfirmDialog } from '../../../../shared/components/confirm-dialog/confirm-dialog';
import { CategoriaService } from '../../../../core/services/categoria';
import { MaterialModule } from '../../../../shared/material/material';
import { CategoriaCreate } from '../categoria-create/categoria-create';
import { MatSnackBar, MatSnackBarRef, SimpleSnackBar } from '@angular/material/snack-bar';
import { MatPaginator } from '@angular/material/paginator';
import { Util } from '../../../../core/services/util/util';

@Component({
  selector: 'app-categoria-list',
  imports: [RouterLink, MaterialModule, ConfirmDialog],
  templateUrl: './categoria-list.html',
  styleUrl: './categoria-list.css',
})
export class CategoriaList implements OnInit {

  isAdmin: any;


  private categoriaService = inject(CategoriaService);
  private dialog = inject(MatDialog);
  constructor(private snackBar: MatSnackBar,
              private util: Util
  ){}

  displayedColumns: string[] = ['idcategory', 'name', 'description', 'acciones'];
  dataSource = new MatTableDataSource<CategoriaElement>();

  ngOnInit(): void {
    this.getCategorias();
    this.isAdmin =  this.util.isAdmin();
  }

  @ViewChild(MatPaginator)
  paginator!: MatPaginator
  openDialog(): void {
    const dialogRef = this.dialog.open(CategoriaCreate, {
      width: '450px',
      disableClose: true
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result === true) {
        this.getCategorias();
      }
    });
  }

  getCategorias(): void {
    this.categoriaService.getObtenerCategorias().subscribe({
      next: (resp: any) => this.processCategoriaResponse(resp),
      error: (error: any) => console.log('Error al obtener categorías:', error)
    });
  }

  processCategoriaResponse(resp: any): void {
    const dataCategory: CategoriaElement[] = [];

    if (resp.metadata[0].code === '00') {
      const listcategoria = resp.categoryResponse.category;

      listcategoria.forEach((element: CategoriaElement) => {
        dataCategory.push({
          idcategory: element.idcategory,
          name: element.name,
          description: element.description
        });
      });

      this.dataSource.data = dataCategory;
      this.dataSource.paginator = this.paginator;
    }
  }

  applyFilter(event: Event): void {
    const valor = (event.target as HTMLInputElement).value;
    this.dataSource.filter = valor.trim().toLowerCase();
  }

  openCategoryDialog(){
    const dialogRef = this.dialog.open(CategoriaCreate,{
      width: '450px'
    });
    dialogRef.afterClosed().subscribe((result:any) =>{
      if(result == 1){
        this.openSnackBar("Categoria agregada", "Exitosa");
        this.getCategorias();
      }else if (result == 2){
        this.openSnackBar("Se produjo un error al guardar la categoria", "Error");
      }
    })
  }

  openSnackBar(message: string, action: string): MatSnackBarRef<SimpleSnackBar>{
    return this.snackBar.open(message, action, {
      duration: 2000
    })
  }


  edit(idcategory: number, name: string, description: string): void{
    const dialogRef = this.dialog.open(CategoriaCreate,{
      data: {
        idcategory: idcategory, 
        name: name, 
        description: description
      }
    });
    dialogRef.afterClosed().subscribe((result:any) =>{
      if(result == 1){
        this.openSnackBar("Categoria Actualizada", "Exitosa");
        this.getCategorias();
      }else if (result == 2){
        this.openSnackBar("Se produjo un error al actuliazr la categoria", "Error");
      }
    })
  }


  deleteCategoria(idcategory: any) {
  const dialogRef = this.dialog.open(ConfirmDialog, {
    width: '400px',
    data: {
      titulo: 'Eliminar Categoría',
      mensaje: '¿Está seguro de que desea eliminar esta categoría?'
    }
  });

  dialogRef.afterClosed().subscribe((confirmado: boolean) => {
    if (confirmado === true) {
      this.categoriaService.deleteCategoria(idcategory).subscribe({
        next: () => {
          this.openSnackBar('Categoría eliminada con éxito', 'Exitosa');
          this.getCategorias(); // Método para refrescar categorías
        },
        error: (err) => this.openSnackBar('Error al eliminar', 'Error')
      });
    }
  });
}
    buscarCategoria(event: Event): void {

  const name = (event.target as HTMLInputElement).value.trim();

  if (name.length === 0) {
    this.getCategorias();
    return;
  }

  this.categoriaService.getcategoriasByName(name).subscribe({
    next: (response: any) => {
      this.dataSource.data = response.categoryResponse.category;
    },
    error: (error: any) => {
      console.log('Error buscando categoría:', error);
    }
  });
}

exportExcel(){
  this.categoriaService.exportCategories()
  .subscribe( (data:any)  =>{
    let file = new Blob([data], {type: 'application/vnd.openxmlformats-officedocument.spreadsheetml.sheet'});
    let fileUrl = URL.createObjectURL(file);
    var anchor = document.createElement("a");
    anchor.download = "category.xlsx";
    anchor.href = fileUrl;
    anchor.click();

    this.openSnackBar("Archivo exportado correctamente", "exitoso")
  }, (error: any) =>{
    this.openSnackBar("No se pudo exportar el archivo", "exitoso")
  })
}
    
}

export interface CategoriaElement {
  idcategory: number;
  name: string;
  description: string;
}