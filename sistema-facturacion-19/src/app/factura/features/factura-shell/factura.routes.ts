import { Routes } from '@angular/router';

export default [
  {
    path: '',
    loadComponent: () => import('../ticket-form/ticket-form.component'),
  },
] as Routes;
