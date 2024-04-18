import { Component, Input } from '@angular/core';
import { Exercise } from 'src/interfaces/exercise.interface';

@Component({
  selector: 'exercise',
  templateUrl: './exercise.component.html',
  styleUrls: ['./exercise.component.css']
})
export class ExerciseComponent {
  @Input()
  exercise!: Exercise;

}
