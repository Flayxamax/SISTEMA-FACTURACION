import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FacturaSearchFormComponent } from './factura-search-form.component';

describe('FacturaSearchFormComponent', () => {
  let component: FacturaSearchFormComponent;
  let fixture: ComponentFixture<FacturaSearchFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [FacturaSearchFormComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(FacturaSearchFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
