import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddAnotherTicketFormComponent } from './add-another-ticket-form.component';

describe('AddAnotherTicketFormComponent', () => {
  let component: AddAnotherTicketFormComponent;
  let fixture: ComponentFixture<AddAnotherTicketFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AddAnotherTicketFormComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AddAnotherTicketFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
