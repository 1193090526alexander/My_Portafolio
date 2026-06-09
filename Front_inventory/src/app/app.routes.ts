import { Routes } from '@angular/router';

export const routes: Routes = [

  {
    path: '',
    redirectTo: 'dashboard',
    pathMatch: 'full',
  },
  {
    path: '',
    loadComponent: () =>
      import('./layouts/main-layout/main-layout').then(m => m.MainLayout),

    children: [
      {
        path: 'dashboard',
        loadComponent: () =>
          import('./features/dashboard/pages/dashboard/dashboard').then(m => m.Dashboard)
      },

      {
        path: 'categorias',
        loadChildren: () =>
          import('./features/categorias/categorias.routes')
            .then(m => m.CATEGORIAS_ROUTES)
      },

      // NUEVA RUTA PRODUCCION
      {
        path: 'produccion',
        loadChildren: () =>
          import('./features/produccion/product.routes')
            .then(m => m.PRODUCCION_ROUTES)
      }

    ]
  },

  {
    path: '**',
    redirectTo: 'dashboard'
  }
];