import { HttpClient } from '@angular/common/http';
import { Component, OnDestroy, OnInit } from '@angular/core';
import { Subscription, finalize } from 'rxjs';
import { Exercise } from 'src/interfaces/exercise.interface';

@Component({
  selector: 'daily-exercises',
  templateUrl: './daily-exercises.component.html',
  styleUrls: ['./daily-exercises.component.css'],
  host: {
    class: 'v-cont fg-1'
  }
})
export class DailyExercisesComponent implements OnInit, OnDestroy{
  private readonly url: string = 'https://api.api-ninjas.com/v1/exercises';
  private readonly key: string = 'X-Api-Key';
  private readonly value: string = 'SeZDpXwIy29zwKD1+1DWLA==NUMLHIWfbXrurDrg';
  private subscription!: Subscription;

  dailyExercises: Array<Exercise> = [];
  loadingComplete: boolean = false; 

  constructor(private http: HttpClient) {}

  ngOnInit(): void {
    this.loadExercises();
  }

  loadExercises(): void {
    this.subscription = this.http.get<Exercise[]>(this.url, {
      headers: { [this.key] : [this.value] }
    })
    .pipe(
      finalize(() => this.loadingComplete = true)
    )
    .subscribe({
      next: (data) => this.dailyExercises = data,
      error: (err) => console.log(err)
    });
  }

  ngOnDestroy(): void {
    if(this.subscription)
      this.subscription.unsubscribe();
  }
}
