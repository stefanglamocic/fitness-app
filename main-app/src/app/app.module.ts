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
import {MatSnackBarModule} from '@angular/material/snack-bar';
import {MatProgressBarModule} from '@angular/material/progress-bar';

import { FitnessProgramsViewComponent } from './home/fitness-programs-view/fitness-programs-view.component';
import { HomeComponent } from './home/home.component';
import { FitnessProgramCardComponent } from './home/fitness-programs-view/fitness-program-card/fitness-program-card.component';
import { FitnessProgramDetailsComponent } from './fitness-program-details/fitness-program-details.component';
import { CommentComponent } from './fitness-program-details/comment/comment.component';
import { DailyExercisesComponent } from './daily-exercises/daily-exercises.component';
import { ExerciseComponent } from './daily-exercises/exercise/exercise.component'; 
import { InfiniteScrollModule } from 'ngx-infinite-scroll';
import { LoginComponent } from './login/login.component';
import { FormsModule } from '@angular/forms';
import { DynamicUserFormComponent } from './dynamic-user-form/dynamic-user-form.component';
import { CreatedFitnessProgramsComponent } from './user/created-fitness-programs/created-fitness-programs.component';
import { FitnessProgramsParticipationsComponent } from './user/fitness-programs-participations/fitness-programs-participations.component';
import { MessagesComponent } from './user/messages/messages.component';

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
    ExerciseComponent,
    LoginComponent,
    DynamicUserFormComponent,
    CreatedFitnessProgramsComponent,
    FitnessProgramsParticipationsComponent,
    MessagesComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    BrowserAnimationsModule,
    MatProgressSpinnerModule,
    MatSnackBarModule,
    MatProgressBarModule,
    InfiniteScrollModule,
    FormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
