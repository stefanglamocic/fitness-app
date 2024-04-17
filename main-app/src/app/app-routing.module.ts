import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { FitnessProgramDetailsComponent } from './fitness-program-details/fitness-program-details.component';

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
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
