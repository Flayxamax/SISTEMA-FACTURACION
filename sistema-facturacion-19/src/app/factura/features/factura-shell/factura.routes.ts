import { Routes } from '@angular/router';

export default [
  {
    path: '',
    loadComponent: () =>
      import('../../../ticket/features/ticket-form/ticket-form.component'),
  },
] as Routes;
