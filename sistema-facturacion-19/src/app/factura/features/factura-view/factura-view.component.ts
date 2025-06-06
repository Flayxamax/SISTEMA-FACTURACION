import { Component, inject } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { FacturaService } from '../../data-access/factura.service';
import { toast } from 'ngx-sonner';

@Component({
  selector: 'app-factura-view',
  imports: [],
  templateUrl: './factura-view.component.html',
  styleUrl: './factura-view.component.css',
})
export default class FacturaViewComponent {
  private _facturaService = inject(FacturaService);
  private token: string | undefined;

  constructor(private route: ActivatedRoute) {
    this.route.params.subscribe((params) => {
      this.token = params['token'];
    });
  }

  downloadXML() {
    if (!this.token) {
      toast.error('Token invalido');
      return;
    }

    this._facturaService.downloadFacturaXml(this.token).subscribe({
      next: ({ blob, filename }) => {
        const url = window.URL.createObjectURL(blob);
        const a = document.createElement('a');
        a.href = url;
        a.download = filename;
        a.style.display = 'none';
        document.body.appendChild(a);
        a.click();
        document.body.removeChild(a);
        window.URL.revokeObjectURL(url);
        toast.success('Descarga factura XML exitosa');
      },
      error: (error) => {
        toast.error(error);
      },
    });
  }
}
