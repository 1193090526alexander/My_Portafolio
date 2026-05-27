import { Component, Inject, inject, OnInit } from '@angular/core';
import { RouterLink } from '@angular/router';
import { CategoriaService } from '../../../../core/services/categoria';
import { MAT_DIALOG_DATA, MatDialog, MatDialogRef } from '@angular/material/dialog';
import { MaterialModule } from '../../../../shared/material/material';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';

@Component({
  selector: 'app-categoria-create',
  imports: [RouterLink, MaterialModule, FormsModule, ReactiveFormsModule],
  templateUrl: './categoria-create.html',
  styleUrl: './categoria-create.css',
})
export class CategoriaCreate implements OnInit{

 categoryForm: FormGroup;

  guardando = false;
  estadoFormulario: string ="Agregar";
  constructor(
    private fb: FormBuilder,
    private categoriaService: CategoriaService,
    private dialogRef: MatDialogRef<CategoriaCreate>,
    @Inject(MAT_DIALOG_DATA) public data: any
  ) {

    console.log(data);
    this.estadoFormulario="Agregar";
    this.categoryForm = this.fb.group({
      name: ['', Validators.required],
      description: ['', Validators.required]

    });
    
    if (data != null ){
      this.updateForm(data);
      this.estadoFormulario="Actualizar";
    }

  }
  ngOnInit(): void {
  }

    guardar(): void {

    if (this.categoryForm.invalid) {
      this.categoryForm.markAllAsTouched();
      return;
    }

    this.guardando = true;

    const body = {
      name: this.categoryForm.get('name')?.value,
      description: this.categoryForm.get('description')?.value,
    };

    if (this.data != null && this.data.idcategory != null) {

      this.categoriaService.updateCategoria(body, this.data.idcategory)
        .subscribe({
          next: () => {
            this.guardando = false;
            this.dialogRef.close(1);
          },
          error: () => {
            this.guardando = false;
            this.dialogRef.close(2);
          }
        });

    } else {

      this.categoriaService.saveCategoria(body)
        .subscribe({
          next: () => {
            this.guardando = false;
            this.dialogRef.close(1);
          },
          error: () => {
            this.guardando = false;
            this.dialogRef.close(2);
          }
        });

    }
  }


  cerrar(): void {
    this.dialogRef.close(3);
  }

  updateForm(data: any){
    this.categoryForm = this.fb.group({
      name: [data.name, Validators.required],
      description: [data.description, Validators.required]
    });
  }
}
