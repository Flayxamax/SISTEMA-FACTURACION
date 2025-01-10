import { TestBed } from '@angular/core/testing';

import { StorageTicketService } from './storage-ticket.service';

describe('StorageTicketService', () => {
  let service: StorageTicketService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(StorageTicketService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
