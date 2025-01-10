import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FacturaFormComponent } from './factura-form.component';

describe('FacturaFormComponent', () => {
  let component: FacturaFormComponent;
  let fixture: ComponentFixture<FacturaFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [FacturaFormComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(FacturaFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
