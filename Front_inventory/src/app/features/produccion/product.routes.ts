import { Routes } from '@angular/router';

export const PRODUCCION_ROUTES: Routes = [
  {
    path: '',
    loadComponent: () =>
      import('./pages/produccion-list/produccion-list').then(m => m.ProduccionList)
  },
  {
    path: 'crear',
    loadComponent: () =>
      import('./pages/produccion-create/produccion-create').then(m => m.ProduccionCreate)
  }
];