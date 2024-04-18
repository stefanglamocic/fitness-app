import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HeaderComponent } from './header/header.component';
import { DropdownMenuComponent } from './header/dropdown-menu/dropdown-menu.component';
import { NewsFeedComponent } from './home/news-feed/news-feed.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import {MatProgressSpinnerModule} from '@angular/material/progress-spinner';

import { FitnessProgramsViewComponent } from './home/fitness-programs-view/fitness-programs-view.component';
import { HomeComponent } from './home/home.component';
import { FitnessProgramCardComponent } from './home/fitness-programs-view/fitness-program-card/fitness-program-card.component';
import { FitnessProgramDetailsComponent } from './fitness-program-details/fitness-program-details.component';
import { CommentComponent } from './fitness-program-details/comment/comment.component';
import { DailyExercisesComponent } from './daily-exercises/daily-exercises.component';
import { ExerciseComponent } from './daily-exercises/exercise/exercise.component'; 

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    DropdownMenuComponent,
    NewsFeedComponent,
    FitnessProgramsViewComponent,
    HomeComponent,
    FitnessProgramCardComponent,
    FitnessProgramDetailsComponent,
    CommentComponent,
    DailyExercisesComponent,
    ExerciseComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    BrowserAnimationsModule,
    MatProgressSpinnerModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
