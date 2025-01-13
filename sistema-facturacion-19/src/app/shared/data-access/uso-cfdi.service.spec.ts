import { TestBed } from '@angular/core/testing';

import { UsoCfdiService } from './uso-cfdi.service';

describe('UsoCfdiService', () => {
  let service: UsoCfdiService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(UsoCfdiService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
