import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { FitnessProgramDetailsComponent } from './fitness-program-details/fitness-program-details.component';
import { DailyExercisesComponent } from './daily-exercises/daily-exercises.component';
import { LoginComponent } from './login/login.component';
import { DynamicUserFormComponent } from './dynamic-user-form/dynamic-user-form.component';
import { CreatedFitnessProgramsComponent } from './user/created-fitness-programs/created-fitness-programs.component';
import { FitnessProgramsParticipationsComponent } from './user/fitness-programs-participations/fitness-programs-participations.component';
import { MessagesComponent } from './user/messages/messages.component';

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
  },
  {
    path: 'user-form',
    component: DynamicUserFormComponent
  },
  {
    path: 'user',
    redirectTo: 'user/created-fitness-programs',
    pathMatch: 'full'
  },
  {
    path: 'user/created-fitness-programs',
    component: CreatedFitnessProgramsComponent,
    title: 'Kreirani fitnes programi'
  },
  {
    path: 'user/fitness-programs-participations',
    component: FitnessProgramsParticipationsComponent,
    title: 'Kupljeni programi'
  },
  {
    path: 'user/messages',
    component: MessagesComponent,
    title: 'Poruke'
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
