import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FitnessProgramsViewComponent } from './fitness-programs-view.component';

describe('FitnessProgramsViewComponent', () => {
  let component: FitnessProgramsViewComponent;
  let fixture: ComponentFixture<FitnessProgramsViewComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ FitnessProgramsViewComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(FitnessProgramsViewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
