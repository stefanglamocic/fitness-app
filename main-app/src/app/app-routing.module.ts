import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { FitnessProgramDetailsComponent } from './fitness-program-details/fitness-program-details.component';
import { DailyExercisesComponent } from './daily-exercises/daily-exercises.component';

const routes: Routes = [
  {
    path: 'home',
    title: 'Početna',
    component: HomeComponent
  },
  {
    path: '',
    redirectTo: '/home',
    pathMatch: 'full'
  },
  {
    path: 'fitness-program/:id',
    component: FitnessProgramDetailsComponent
  },
  {
    path: 'daily-exercises',
    title: 'Dnevni prijedlozi',
    component: DailyExercisesComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
