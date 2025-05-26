import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GenerateTicketComponent } from './generate-ticket.component';

describe('GenerateTicketComponent', () => {
  let component: GenerateTicketComponent;
  let fixture: ComponentFixture<GenerateTicketComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [GenerateTicketComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(GenerateTicketComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
