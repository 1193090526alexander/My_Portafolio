import { ChangeDetectorRef, Component, inject, Inject, OnInit } from '@angular/core';
import { RouterLink } from '@angular/router';
import { MaterialModule } from '../../../../shared/material/material';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { ProductoService } from '../../../../core/services/product/product';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { CategoriaService } from '../../../../core/services/categoria';

export interface Category {
  idcategory: number;
  name: string;
  description: string;
}

@Component({
  selector: 'app-produccion-create',
  imports: [RouterLink, MaterialModule, FormsModule, ReactiveFormsModule],
  templateUrl: './produccion-create.html',
  styleUrl: './produccion-create.css',
})
export class ProduccionCreate implements OnInit {

  prodcutForm: FormGroup;
  guardando = false;
  estadoFormulario: string = "Agregar";
  categories: Category[] = [];
  loadingCategories = true;
  file: any;

  selectedFile!: File;
  imagePreview: string | ArrayBuffer | null = null;
  
  private categoriaservice = inject(CategoriaService);
  private prodcutoservice = inject(ProductoService);

  constructor(
    private fb: FormBuilder,
    private cdr: ChangeDetectorRef,
    private dialogRef: MatDialogRef<ProduccionCreate>,
    @Inject(MAT_DIALOG_DATA) public data: any
  ) {
    console.log('Datos recibidos en el constructor:', data);

    // 1. Inicializamos el formulario base en limpio. Nada más.
    this.prodcutForm = this.fb.group({
      name: ['', Validators.required],
      price: ['', Validators.required],
      quantity: ['', Validators.required],
      category: ['', Validators.required],
      picture: [null, Validators.required]
    });
  }
  
  ngOnInit(): void {
    // 2. Cargamos las categorías de la base de datos
    this.getCategories();

    // 3. 🔥 SOLUCIÓN: Movemos la lógica de actualización aquí a ngOnInit
    if (this.data != null) {
      this.estadoFormulario = "Actualizar";
      this.updateForm(this.data);
    }
  }

  onFileSelected(event: any): void {
    if (event.target.files.length > 0) {
      this.file = event.target.files[0];
      this.prodcutForm.patchValue({
        picture: this.file
      });

      this.prodcutForm.get('picture')?.updateValueAndValidity();
      
      const reader = new FileReader();
      reader.onload = () => {
        this.imagePreview = reader.result;
        this.cdr.detectChanges(); 
      };
      reader.readAsDataURL(this.file);
    }
  }

  guardar(): void {
    if (this.prodcutForm.invalid) {
      this.prodcutForm.markAllAsTouched();
      return;
    }

    if (this.estadoFormulario === 'Agregar' && !this.file) {
      alert('Debe seleccionar una imagen');
      return;
    }

    this.guardando = true;

    const formData = new FormData();
    formData.append('name', this.prodcutForm.get('name')?.value);
    formData.append('price', this.prodcutForm.get('price')?.value);
    formData.append('quantity', this.prodcutForm.get('quantity')?.value);
    formData.append('category', this.prodcutForm.get('category')?.value);

    if (this.file) {
      formData.append('picture', this.file, this.file.name);
    }

    if (this.data != null && this.data.id != null) {
      this.prodcutoservice.updateProdcut(formData, this.data.id)
        .subscribe({
          next: () => {
            this.guardando = false;
            this.dialogRef.close(1);
          },
          error: (err) => {
            console.error(err);
            this.guardando = false;
            this.dialogRef.close(2);
          }
        });
    } else {
      this.prodcutoservice.saveProduct(formData)
        .subscribe({
          next: () => {
            this.guardando = false;
            this.dialogRef.close(1);
          },
          error: (err) => {
            console.error(err);
            this.guardando = false;
            this.dialogRef.close(2);
          }
        });
    }
  }

  cerrar(): void {
    this.dialogRef.close(3);
  }

updateForm(data: any) {
    // 1. Quitamos dinámicamente la validación requerida de la imagen
    this.prodcutForm.get('picture')?.clearValidators();
    this.prodcutForm.get('picture')?.updateValueAndValidity();

    // 2. Mapeo seguro de la categoría (aquí declaras 'categoryId')
    const categoryId = data.category?.idcategory || data.category?.id || '';

    // 3. Rellenamos el formulario reactivo
    this.prodcutForm.patchValue({
      name: data.name,
      price: data.price,
      quantity: data.quantity,
      category: categoryId // 🔥 SOLUCIÓN: Cambiado 'category' por 'categoryId'
    });

    // 4. 🔥 IMPORTANTE: Aseguramos que 'this.file' empiece limpio 
    // para que la lógica de conversión base64 del método guardar() sepa que NO subiste un archivo nuevo.
    this.file = null; 

    // 5. Si trae imagen previa codificada en base64 desde la tabla, la renderizamos
    if (data.picture) {
      this.imagePreview = data.picture;
      this.cdr.detectChanges(); // Sincroniza la vista de inmediato
    }
  }

  getCategories(): void {
    this.categoriaservice.getObtenerCategorias()
      .subscribe({
        next: (data: any) => {
          this.categories = data.categoryResponse.category || [];
          this.loadingCategories = false;
          this.cdr.detectChanges();
        },
        error: (err) => {
          console.error(err);
          this.loadingCategories = false;
        }
      });
  }
}