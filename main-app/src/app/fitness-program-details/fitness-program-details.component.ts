import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { FitnessProgramsService } from '../home/fitness-programs-view/service/fitness-programs.service';
import { FitnessProgram } from 'src/interfaces/fitness-program.interface';
import { CommentInterface } from 'src/interfaces/comment.interface';
import { Title } from '@angular/platform-browser';
import { finalize } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { UserService } from '../service/user.service';

@Component({
  selector: 'fitness-program-details',
  templateUrl: './fitness-program-details.component.html',
  styleUrls: ['./fitness-program-details.component.css']
})
export class FitnessProgramDetailsComponent implements OnInit {
  id: number = 0;
  image: number = 0;

  fitnessProgram: FitnessProgram = {
    id: this.id,
    name: 'Nepoznato',
    price: 0,
    hidden: false
  };

  constructor(private route: ActivatedRoute,
    private title: Title,
    private fitnessProgramsService: FitnessProgramsService,
    private userService: UserService,
    private http: HttpClient) {
    this.id = Number(this.route.snapshot.params['id']);
  }

  ngOnInit(): void {
    this.getDetails();
  }

  getDetails(): void {
    this.fitnessProgramsService.getFitnessProgram(this.id)
      .pipe(
        finalize(() => {
          this.title.setTitle(this.fitnessProgram.name);
        })
      )
      .subscribe(
        {
          next: (data) => this.fitnessProgram = data,
          error: (err) => console.log(err)
        }
      );
  }

  concatAttributes(): string {
    return this.fitnessProgram.category?.attributes?.map(attr => attr.name).join(', ') || '';
  }

  getCommentNumber(comments: Array<CommentInterface>): number {
    let temp = 0;
    if (comments.length === 0)
      return 0;
    for (let c of comments) {
      temp += 1 + this.getCommentNumber(c.childComments);
    }

    return temp;
  }
  
  cycleImages(direction: number = 1): void {
    let length = 0;
    if (this.fitnessProgram.images?.length)
      length = this.fitnessProgram.images.length;
    this.image = ((this.image + direction) < 0 ? length - 1 : this.image + direction) % length;
  }

  userLoggedIn(): boolean {
    return this.userService.loggedIn;
  }
}
