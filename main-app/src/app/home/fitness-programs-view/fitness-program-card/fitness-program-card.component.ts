import { Component, Input } from '@angular/core';
import { FitnessProgram } from 'src/interfaces/fitness-program.interface';

@Component({
  selector: 'fitness-program-card',
  templateUrl: './fitness-program-card.component.html',
  styleUrls: ['./fitness-program-card.component.css']
})
export class FitnessProgramCardComponent {
  @Input()
  fitnessProgram!: FitnessProgram;
}
