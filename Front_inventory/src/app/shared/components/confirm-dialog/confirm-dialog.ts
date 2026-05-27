import { Component, Inject, OnInit } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { MaterialModule } from '../../material/material';
import { CategoriaService } from '../../../core/services/categoria';

@Component({
  selector: 'app-confirm-dialog',
  imports: [MaterialModule],
  templateUrl: './confirm-dialog.html',
  styleUrl: './confirm-dialog.css',
})
export class ConfirmDialog implements OnInit{

   constructor(
    private dialogRef: MatDialogRef<ConfirmDialog>,
    private categoriaService: CategoriaService,
    @Inject(MAT_DIALOG_DATA) public data: any
  ) {}
  ngOnInit(): void {
  }

  cancelar(): void {
    this.dialogRef.close(3);
  }

  confirmar(): void {
    if(this.data != null){
      this.categoriaService.deleteCategoria(this.data.idcategory).subscribe( (data: any) =>{
        this.dialogRef.close(1);
      },(error:any)=>{
        this.dialogRef.close(2);
      }
    )
  }else{
    this.dialogRef.close(2);
  }
}
}
