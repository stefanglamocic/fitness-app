import { AfterViewInit, Component, OnDestroy, OnInit, QueryList, ViewChildren } from '@angular/core';
import { FitnessProgramsService } from './service/fitness-programs.service';
import { Subscription, finalize, skip } from 'rxjs';
import { FitnessProgram } from 'src/interfaces/fitness-program.interface';
import { Router } from '@angular/router';
import { InputService } from 'src/app/service/input.service';
import { FitnessProgramCardComponent } from './fitness-program-card/fitness-program-card.component';

@Component({
  selector: 'fitness-programs-view',
  templateUrl: './fitness-programs-view.component.html',
  styleUrls: ['./fitness-programs-view.component.css']
})
export class FitnessProgramsViewComponent implements OnInit, AfterViewInit, OnDestroy{
  fitnessPrograms: Array<FitnessProgram> = [];
  searchTerm: string = '';
  contentLoaded: boolean = false;
  private subscriptions: Array<Subscription> = []

  @ViewChildren('cardRef')
  cards!: QueryList<FitnessProgramCardComponent>;

  constructor(private fitnessProgramsService: FitnessProgramsService,
    private router: Router,
    private inputService: InputService) {}

  ngOnInit(): void {
    this.getFitnessPrograms();
    this.getSearchTerm();
  }

  ngAfterViewInit(): void {
  }

  ngOnDestroy(): void {
    this.subscriptions.forEach(sub => sub.unsubscribe());
  }

  getFitnessPrograms(): void {
    let subscription = this.fitnessProgramsService.getAll()
    .pipe(
      finalize(() => this.contentLoaded = true)
    )
    .subscribe(
      {
        next: (data) => this.fitnessPrograms = data,
        error: (err) => console.log(err)
      }
    );

    this.subscriptions.push(subscription);
  }

  getSearchTerm(): void {
    let sub = this.inputService.currentMessage
      .pipe(
        skip(1) //preskacem prvu emitovanu vrijednost jer nije sve inicijalizovano
      )
      .subscribe(msg => {
        this.searchTerm = msg;
        this.filterCards();
      });

    this.subscriptions.push(sub);
  }

  private clearDisplayStyle() {
    this.cards.forEach(c => c.setVisible(true));
  }

  private filterCards(): void {
    this.clearDisplayStyle();

    this.cards
          .filter(card => !card.fitnessProgram.name
              .toLowerCase()
              .includes(this.searchTerm))
          .forEach(card => card.setVisible(false));
  }

  routeToPage(fitnessProgram: FitnessProgram): void {
    this.router.navigate(['fitness-program', fitnessProgram.id]);
  }
}
