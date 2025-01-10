import { FormGroup } from '@angular/forms';

export const isRequired = (
  field: 'folio' | 'codigoFacturacion' | 'sucursal',
  form: FormGroup
) => {
  const control = form.get(field);

  return control && control.touched && control.hasError('required');
};
