import { Routes } from '@angular/router';

export const CATEGORIAS_ROUTES: Routes = [
  {
    path: '',
    loadComponent: () =>
      import('./pages/categoria-list/categoria-list').then(m => m.CategoriaList)
  },
  {
    path: 'crear',
    loadComponent: () =>
      import('./pages/categoria-create/categoria-create').then(m => m.CategoriaCreate)
  }
];