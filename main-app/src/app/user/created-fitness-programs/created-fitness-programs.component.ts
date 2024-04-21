import { Component, OnDestroy, OnInit } from '@angular/core';
import { Subscription, filter } from 'rxjs';
import { UserService } from 'src/app/service/user.service';
import { FitnessProgram } from 'src/interfaces/fitness-program.interface';

@Component({
  selector: 'created-fitness-programs',
  templateUrl: './created-fitness-programs.component.html',
  styleUrls: ['./created-fitness-programs.component.css']
})
export class CreatedFitnessProgramsComponent implements OnInit, OnDestroy{
  private subscriptions: Subscription[] = [];

  createdFitnessPrograms: Array<FitnessProgram> = [];

  constructor(private userService: UserService){}

  ngOnInit(): void {
    this.getCreatedFitnessPrograms();
  }

  ngOnDestroy(): void {
    this.subscriptions.forEach(sub => sub.unsubscribe());
  }

  getCreatedFitnessPrograms(): void {
    let sub = this.userService.getCreatedFitnessPrograms()
      .subscribe(
        {
          next: (data) => this.createdFitnessPrograms = data
        }
      );

    this.subscriptions.push(sub);
  }

}
