import { Component, ElementRef, OnDestroy, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Subscription, filter } from 'rxjs';
import { FitnessProgramsService } from 'src/app/home/fitness-programs-view/service/fitness-programs.service';
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
  newFitnessProgram: FitnessProgram = {
    hidden: false,
    id: 0,
    name: '',
    price: 0
  };

  constructor(private userService: UserService,
    private fitnessProgramsService: FitnessProgramsService,
    private snackBar: MatSnackBar){}

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

  deleteProgram(id: number) {
    let sub = this.fitnessProgramsService.removeFitnessProgram(id).subscribe();
    this.createdFitnessPrograms = 
      this.createdFitnessPrograms.filter(fp => fp.id !== id);
    this.subscriptions.push(sub);
  }

  showModal(modal: HTMLDialogElement) {
    modal.showModal();
    modal.querySelector('form')?.reset();
  }

  reset(form: NgForm, modal: HTMLDialogElement): void {
    form.reset();
    modal.close();
  }

  add(form: NgForm, modal: HTMLDialogElement): void {
    console.log(this.newFitnessProgram);

    form.reset();
    modal.close();
  }

}
