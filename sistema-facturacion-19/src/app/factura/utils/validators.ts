import { FormGroup } from '@angular/forms';

export const isRequired = (
  field: 'rfc' | 'nombre' | 'direccion' | 'colonia' | 'ciudad' | 'cp' | 'estado' | 'regimenFiscal' | 'usoCfdi',
  form: FormGroup
) => {
  const control = form.get(field);

  return control && control.touched && control.hasError('required');
};
