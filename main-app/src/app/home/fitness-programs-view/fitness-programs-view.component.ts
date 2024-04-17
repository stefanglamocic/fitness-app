import { Component, OnDestroy, OnInit } from '@angular/core';
import { FitnessProgramsService } from './service/fitness-programs.service';
import { Subscription } from 'rxjs';
import { FitnessProgram } from 'src/interfaces/fitness-program.interface';
import { Router } from '@angular/router';

@Component({
  selector: 'fitness-programs-view',
  templateUrl: './fitness-programs-view.component.html',
  styleUrls: ['./fitness-programs-view.component.css']
})
export class FitnessProgramsViewComponent implements OnInit, OnDestroy{
  fitnessPrograms: Array<FitnessProgram> = [];
  private subscriptions: Array<Subscription> = []

  constructor(private fitnessProgramsService: FitnessProgramsService,
    private router: Router) {}

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

  routeToPage(fitnessProgram: FitnessProgram): void {
    this.router.navigate(['fitness-program', fitnessProgram.id]);
  }
}
