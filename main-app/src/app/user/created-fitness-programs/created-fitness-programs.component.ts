import { Component, ElementRef, OnDestroy, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Subscription, filter, finalize } from 'rxjs';
import { FitnessProgramsService } from 'src/app/home/fitness-programs-view/service/fitness-programs.service';
import { UserService } from 'src/app/service/user.service';
import { Category } from 'src/interfaces/category.interface';
import { FitnessProgram } from 'src/interfaces/fitness-program.interface';
import { Instructor } from 'src/interfaces/instructor.interface';

@Component({
  selector: 'created-fitness-programs',
  templateUrl: './created-fitness-programs.component.html',
  styleUrls: ['./created-fitness-programs.component.css']
})
export class CreatedFitnessProgramsComponent implements OnInit, OnDestroy{
  private subscriptions: Subscription[] = [];

  category: Category = {
    id: 0,
    attributes: [],
    name: ''
  };

  instructor: Instructor = {
    id: 0,
    contact: '',
    information: '',
    name: ''
  };

  name: string = '';
  duration: number = 0;
  price: number = 0;
  difficultyLevel: string = '';
  location: string = '';
  description: string = '';

  createdFitnessPrograms: Array<FitnessProgram> = [];
  categories: Array<Category> = [];
  instructors: Array<Instructor> = [];
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
    let sub1 = this.fitnessProgramsService.getCategories()
      .subscribe(data => this.categories = data);
    let sub2 = this.fitnessProgramsService.getInstructors()
      .subscribe(data => this.instructors = data);

    this.subscriptions.push(sub1, sub2);

    modal.showModal();
    modal.querySelector('form')?.reset();
  }

  reset(form: NgForm, modal: HTMLDialogElement): void {
    form.reset();
    modal.close();
  }

  add(form: NgForm, modal: HTMLDialogElement): void {
    let images: {path: string}[] = [];
    this.convertTextToImageArray().forEach(i => images.push({path: i}));
    this.newFitnessProgram.images = images;
    this.newFitnessProgram.createdBy = this.userService.currentUser;
    this.newFitnessProgram.category = this.category;
    this.newFitnessProgram.instructor = this.instructor;
    this.newFitnessProgram.difficultyLevel = this.difficultyLevel;
    this.newFitnessProgram.description = this.description;
    this.newFitnessProgram.location = this.location;
    this.newFitnessProgram.name = this.name;
    this.newFitnessProgram.duration = this.duration;
    this.newFitnessProgram.price = this.price;

    this.fitnessProgramsService.uploadImages(this.newFitnessProgram.images)
      .pipe(
        finalize(() => this.addFitnessProgram())
      )
      .subscribe();
    
    //this.addFitnessProgram();

    form.reset();
    modal.close();
  }

  async uploadImages(images: {path: string}[]): Promise<void>{
    await new Promise<void>((resolve, reject) => {
      let sub = this.fitnessProgramsService.uploadImages(images)
        .subscribe(data => resolve());
      this.subscriptions.push(sub);
    });
  }

  addFitnessProgram(): void {
    let sub = this.fitnessProgramsService.addFitnessProgram(this.newFitnessProgram)
      .subscribe({
        next: (data) => {
          this.createdFitnessPrograms.push(data);
          this.snackBar.open('Uspješno dodat novi program.', '', {duration: 4000});
        },
        error: (err) => this.snackBar.open('Greška pri dodavanju programa.', '', {duration: 4000})
      });

    this.subscriptions.push(sub);
  }

  getAttributes(category: Category): string {
    return category.attributes.map(a => a.name).join(', ');
  }

  convertTextToImageArray() {
    let text: string = (document.querySelector('#images') as HTMLTextAreaElement).value;
    return text.split('\n');
  }

}
