import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HeaderComponent } from './header/header.component';
import { DropdownMenuComponent } from './header/dropdown-menu/dropdown-menu.component';
import { NewsFeedComponent } from './home/news-feed/news-feed.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { ScrollingModule } from '@angular/cdk/scrolling';
import { FitnessProgramsViewComponent } from './home/fitness-programs-view/fitness-programs-view.component';
import { HomeComponent } from './home/home.component';
import { FitnessProgramCardComponent } from './home/fitness-programs-view/fitness-program-card/fitness-program-card.component'; 

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    DropdownMenuComponent,
    NewsFeedComponent,
    FitnessProgramsViewComponent,
    HomeComponent,
    FitnessProgramCardComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    BrowserAnimationsModule,
    ScrollingModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
