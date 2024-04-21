import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CreatedFitnessProgramsComponent } from './created-fitness-programs.component';

describe('CreatedFitnessProgramsComponent', () => {
  let component: CreatedFitnessProgramsComponent;
  let fixture: ComponentFixture<CreatedFitnessProgramsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CreatedFitnessProgramsComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CreatedFitnessProgramsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
