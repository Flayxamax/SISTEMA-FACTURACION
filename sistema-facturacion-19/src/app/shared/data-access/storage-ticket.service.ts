import { Injectable } from '@angular/core';
import { Ticket } from '../interfaces/ticket';
import { Observable, of } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class StorageTicketService {
  saveTicket(newTickets: Ticket[]): void {
    let existingTickets = JSON.parse(localStorage.getItem('tickets') || '[]');

    if (!Array.isArray(existingTickets)) {
      existingTickets = [];
    }

    newTickets.forEach((newTicket) => {
      if (
        existingTickets.some((ticket: Ticket) => ticket.id === newTicket.id)
      ) {
        throw new Error(`Ticket with id ${newTicket.id} already exists.`);
      }
    });

    const updatedTickets = existingTickets.concat(newTickets);

    localStorage.setItem('tickets', JSON.stringify(updatedTickets));
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
