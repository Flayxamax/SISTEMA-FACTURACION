import { Routes } from '@angular/router';

export const routes: Routes = [
  {
    path: '',
    redirectTo: 'ticket',
    pathMatch: 'full',
  },
  {
    path: 'ticket',
    loadChildren: () => import('./factura/features/factura-shell/factura.routes'),
  },
  {
    path: '**',
    redirectTo: 'ticket',
  },
];
