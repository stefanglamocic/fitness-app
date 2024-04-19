import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { FitnessProgramDetailsComponent } from './fitness-program-details/fitness-program-details.component';
import { DailyExercisesComponent } from './daily-exercises/daily-exercises.component';
import { LoginComponent } from './login/login.component';

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
  },
  {
    path: 'login',
    title: 'Prijavi se',
    component: LoginComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
