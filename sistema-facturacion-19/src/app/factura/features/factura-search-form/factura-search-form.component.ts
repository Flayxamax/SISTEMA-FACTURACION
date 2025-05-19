import { Component, inject } from '@angular/core';
import { FormBuilder, ReactiveFormsModule, Validators } from '@angular/forms';
import { isRequired } from '../../utils/validators';
import { FacturaService } from '../../data-access/factura.service';
import { toast } from 'ngx-sonner';
import { Router } from '@angular/router';
import { ZXingScannerModule } from '@zxing/ngx-scanner';
import { BarcodeFormat } from '@zxing/library';

@Component({
  selector: 'app-factura-search-form',
  imports: [ReactiveFormsModule, ZXingScannerModule],
  templateUrl: './factura-search-form.component.html',
  styleUrl: './factura-search-form.component.css',
})
export default class FacturaSearchFormComponent {
  private _formBuilder = inject(FormBuilder);
  private _facturaService = inject(FacturaService);
  private _router = inject(Router);

  showScanner = false;
  allowedFormats = [BarcodeFormat.QR_CODE];
  scannedResult: string | null = null;
  hasDevices = false;
  availableDevices: MediaDeviceInfo[] = [];
  selectedDevice: MediaDeviceInfo | undefined; // Fix: Use `undefined` instead of `null`

  onCodeResult(result: string) {
    try {
      const parsedJSONResult = JSON.parse(result);
      this.scannedResult = result;

      const folio = parsedJSONResult.folio;
      const codigoFacturacion = parsedJSONResult.codigoFacturacion;

      if (folio && codigoFacturacion) {
        this.ticketForm.patchValue({
          folio: folio,
          codigoFacturacion: codigoFacturacion,
        });
        toast.success('Datos escaneados correctamente');
        this.showScanner = false;
      } else {
        toast.error('QR no contiene datos válidos');
      }
    } catch (error) {
      console.error('Error parsing QR result:', error);
      toast.error('Error al leer el código QR');
    }
  }

  onDeviceSelectChange(event: Event) {
    const target = event.target as HTMLSelectElement; // Fix: Properly typecast EventTarget
    this.selectedDevice = this.availableDevices.find((device) => device.deviceId === target.value);
  }

  onHasDevices(hasDevices: boolean) {
    this.hasDevices = hasDevices;
  }

  onDevicesFound(devices: MediaDeviceInfo[]) {
    this.availableDevices = devices;
    if (devices.length > 0) {
      this.selectedDevice = devices[0]; // Fix: Ensure a device is selected initially
    }
  }

  onError(error: any) {
    console.error('Barcode scanning error:', error);
  }

  openScanner() {
    this.showScanner = true;
  }

  isRequired(field: 'folio' | 'codigoFacturacion') {
    return isRequired(field, this.ticketForm);
  }

  ticketForm = this._formBuilder.group({
    folio: this._formBuilder.control('', [Validators.required, Validators.pattern('^[0-9]*$')]),
    codigoFacturacion: this._formBuilder.control('', [Validators.required, Validators.pattern('^[0-9]*$')]),
  });

  async onSubmit() {
    if (this.ticketForm.invalid) return;

    const { folio, codigoFacturacion } = this.ticketForm.value;

    this._facturaService.getFacturaByTicket(folio!, codigoFacturacion!).subscribe(
      (token: string) => {
        if (token) {
          toast.success('Factura encontrada');
          this._router.navigate(['ticket/facturacion/', token]);
        }
      },
      (error) => {
        toast.error(error);
      }
    );
  }
}
