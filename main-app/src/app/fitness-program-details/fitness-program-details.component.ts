import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { FitnessProgramsService } from '../home/fitness-programs-view/service/fitness-programs.service';
import { FitnessProgram } from 'src/interfaces/fitness-program.interface';

@Component({
  selector: 'fitness-program-details',
  templateUrl: './fitness-program-details.component.html',
  styleUrls: ['./fitness-program-details.component.css'],
  host: {
    class: 'v-cont fg-1 crossa-center'
  }
})
export class FitnessProgramDetailsComponent implements OnInit{
  id: number = 0;
  fitnessProgram: FitnessProgram = {
    id: this.id,
    name: 'Nepoznato',
    price: 0
  };

  constructor(private route: ActivatedRoute, 
    private fitnessProgramsService: FitnessProgramsService) {
    this.id = Number(this.route.snapshot.params['id']);
  }

  ngOnInit(): void {
    this.getDetails();
  }

  getDetails(): void {
    this.fitnessProgramsService.getFitnessProgram(this.id)
      .subscribe(
        {
          next: (data) => this.fitnessProgram = data,
          error: (err) => console.log(err)
        }
      );
  }

  concatAttributes(): string {
    return this.fitnessProgram.category?.attributes?.map(attr => attr.name).join(', ') || '';
  }
}
