import { Component, input } from '@angular/core';
import { Ticket } from '../../../shared/interfaces/ticket';

@Component({
  selector: 'app-table',
  imports: [],
  templateUrl: './table.component.html',
  styleUrl: './table.component.css',
  standalone: true,
})
export class TableComponent {
  tickets = input.required<Ticket[]>();

  constructor() {
    console.log(this.tickets);
  }
}
