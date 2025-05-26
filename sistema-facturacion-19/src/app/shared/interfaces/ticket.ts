import { Producto } from './producto';
import { Sucursal } from './sucursal';
import { Factura } from './factura';

export interface Ticket {
  id: number;
  date: string;
  folio: number;
  codigoFacturacion: number;
  sucursal: Sucursal;
  total: number;
  tipoPago: string;
  factura: Factura | null;
  productos: Producto[];
  subTotal: number;
}
