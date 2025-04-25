import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ClientScheduleTableComponent } from './client-schedule-table.component';

describe('ClientScheduleTableComponent', () => {
  let component: ClientScheduleTableComponent;
  let fixture: ComponentFixture<ClientScheduleTableComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ClientScheduleTableComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ClientScheduleTableComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
