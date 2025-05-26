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
  {
    path: 'buscar-factura',
    loadComponent: () => import('../factura-search-form/factura-search-form.component'),
  },
  {
    path: 'generar-ticket',
    loadComponent: () => import('../../../ticket/features/generate-ticket/generate-ticket.component'),
  },
] as Routes;
