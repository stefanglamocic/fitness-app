import { Component, OnDestroy, OnInit } from '@angular/core';
import { FitnessProgramsService } from './service/fitness-programs.service';
import { Subscription, finalize } from 'rxjs';
import { FitnessProgram } from 'src/interfaces/fitness-program.interface';

@Component({
  selector: 'fitness-programs-view',
  templateUrl: './fitness-programs-view.component.html',
  styleUrls: ['./fitness-programs-view.component.css']
})
export class FitnessProgramsViewComponent implements OnInit, OnDestroy{
  fitnessPrograms: Array<FitnessProgram> = [];
  private subscriptions: Array<Subscription> = []

  constructor(private fitnessProgramsService: FitnessProgramsService) {}

  ngOnInit(): void {
    this.getFitnessPrograms();
  }

  ngOnDestroy(): void {
    this.subscriptions.forEach(sub => sub.unsubscribe());
  }

  getFitnessPrograms(): void {
    let subscription = this.fitnessProgramsService.getAll()
    .subscribe(
      {
        next: (data) => this.fitnessPrograms = data,
        error: (err) => console.log(err)
      }
    );

    this.subscriptions.push(subscription);
  }
}
