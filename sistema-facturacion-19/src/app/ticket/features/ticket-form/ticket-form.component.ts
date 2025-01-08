import { Component, inject } from '@angular/core';
import {
  FormBuilder,
  FormGroup,
  ReactiveFormsModule,
  Validators,
} from '@angular/forms';
import { SucursalService } from '../../../shared/data-access/sucursal.service';
import { Sucursal } from '../../../shared/interfaces/sucursal';

@Component({
  selector: 'app-ticket-form',
  imports: [ReactiveFormsModule],
  templateUrl: './ticket-form.component.html',
  styleUrl: './ticket-form.component.css',
})
export default class TicketFormComponent {
  private _formBuilder = inject(FormBuilder);
  private _sucursalService = inject(SucursalService);
  sucursales: Sucursal[] = [];

  ticketForm = this._formBuilder.group({
    folio: this._formBuilder.control('', [
      Validators.required,
      Validators.pattern('^[0-9]*$'),
    ]),
    codigoFacturacion: this._formBuilder.control('', [
      Validators.required,
      Validators.pattern('^[0-9]*$'),
    ]),
    sucursal: this._formBuilder.control('', Validators.required),
  });

  constructor() {
    this._sucursalService.getAllSucursal().subscribe(
      (sucursales) => {
        this.sucursales = sucursales;
      },
      (error) => {
        console.error(error);
      }
    );
  }
  async onSubmit() {
    if (this.ticketForm.invalid) {
      return;
    }
  }
}
