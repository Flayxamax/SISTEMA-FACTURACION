import { Component, input } from '@angular/core';
import { Ticket } from '../../../shared/interfaces/ticket';
import { DateformatPipe } from './pipes/dateformat.pipe';

@Component({
  selector: 'app-table',
  imports: [DateformatPipe],
  templateUrl: './table.component.html',
  styleUrl: './table.component.css',
  standalone: true,
})
export class TableComponent {
  tickets = input.required<Ticket[]>();

  constructor() {}
}
