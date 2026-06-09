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
    @Inject(MAT_DIALOG_DATA) public data: { titulo: string; mensaje: string }
  ) {}

  ngOnInit(): void {}

  cancelar(): void {
    this.dialogRef.close(false); // Retorna false si cancela
  }

  confirmar(): void {
    this.dialogRef.close(true); // Retorna true si confirma
  }
}
