import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddFitnessProgramComponent } from './add-fitness-program.component';

describe('AddFitnessProgramComponent', () => {
  let component: AddFitnessProgramComponent;
  let fixture: ComponentFixture<AddFitnessProgramComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AddFitnessProgramComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AddFitnessProgramComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
