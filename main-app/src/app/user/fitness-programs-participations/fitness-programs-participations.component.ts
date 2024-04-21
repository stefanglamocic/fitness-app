import { Component, OnDestroy, OnInit } from '@angular/core';
import { Subscription } from 'rxjs';
import { UserService } from 'src/app/service/user.service';
import { FitnessProgram } from 'src/interfaces/fitness-program.interface';

@Component({
  selector: 'fitness-programs-participations',
  templateUrl: './fitness-programs-participations.component.html',
  styleUrls: ['./fitness-programs-participations.component.css']
})
export class FitnessProgramsParticipationsComponent implements OnInit, OnDestroy{
  private subscriptions: Subscription[] = [];

  fitnessPrograms: Array<FitnessProgram> = [];

  constructor(private userService: UserService) {}

  ngOnInit(): void {
    this.getFitnessPrograms();
  }

  ngOnDestroy(): void {
    this.subscriptions.forEach(sub => sub.unsubscribe);
  }

  getFitnessPrograms(): void {
    let sub = this.userService.getFitnessProgramParticipations()
      .subscribe({
        next: (data) => this.fitnessPrograms = data
      });

    this.subscriptions.push(sub);
  }

}
