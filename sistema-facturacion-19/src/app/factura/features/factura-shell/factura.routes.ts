import { Routes } from '@angular/router';

export default [
  {
    path: '',
    loadComponent: () => import('../../../ticket/features/ticket-form/ticket-form.component'),
  },
  {
    path: 'facturacion',
    loadComponent: () => import('../factura-form/factura-form.component'),
  },
  {
    path: 'facturacion/:token',
    loadComponent: () => import('../factura-view/factura-view.component'),
  },
] as Routes;
