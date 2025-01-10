import { Injectable } from '@angular/core';
import { Ticket } from '../interfaces/ticket';
import { Observable, of } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class StorageTicketService {
  saveTicket(tickets: Ticket[]): void {
    localStorage.setItem('tickets', JSON.stringify(tickets));
  }

  loadTickets(): Observable<Ticket[]> {
    const tickets = localStorage.getItem('tickets');
    return of(tickets ? JSON.parse(tickets) : []);
  }

  deleteTickets(): void {
    localStorage.removeItem('tickets');
  }

  constructor() {}
}
