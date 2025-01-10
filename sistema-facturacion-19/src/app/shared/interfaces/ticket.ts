import { Producto } from './producto';
import { Sucursal } from './sucursal';

export interface Ticket {
  id: number;
  date: string;
  folio: number;
  codigoFacturacion: number;
  sucursal: Sucursal;
  total: number;
  tipoPago: string;
  factura: any;
  productos: Producto[];
  iva: number;
}
