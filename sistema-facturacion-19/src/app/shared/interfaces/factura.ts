import { Regimen } from './regimen';
import { Ticket } from './ticket';
import { UsoCfdi } from './uso-cfdi';

export interface Factura {
  id: number;
  rfc: string;
  nombre: string;
  direccion: string;
  colonia: string;
  ciudad: string;
  estado: string;
  codigoPostal: string;
  regimenFiscal: Regimen;
  usoCFDI: UsoCfdi;
  subTotal: number;
  total: number;
  fechaEmision: Date;
  fechaEmisionCertificado: Date;
  tickets: Ticket[];
}
