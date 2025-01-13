import { TestBed } from '@angular/core/testing';

import { RegimenFiscalService } from './regimen-fiscal.service';

describe('RegimenFiscalService', () => {
  let service: RegimenFiscalService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(RegimenFiscalService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
