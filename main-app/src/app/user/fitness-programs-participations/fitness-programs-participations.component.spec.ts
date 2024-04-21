import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FitnessProgramsParticipationsComponent } from './fitness-programs-participations.component';

describe('FitnessProgramsParticipationsComponent', () => {
  let component: FitnessProgramsParticipationsComponent;
  let fixture: ComponentFixture<FitnessProgramsParticipationsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ FitnessProgramsParticipationsComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(FitnessProgramsParticipationsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
