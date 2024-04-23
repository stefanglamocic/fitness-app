import { Component, OnDestroy, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { FitnessProgramsService, PostCommentTemplate } from '../home/fitness-programs-view/service/fitness-programs.service';
import { FitnessProgram } from 'src/interfaces/fitness-program.interface';
import { CommentInterface } from 'src/interfaces/comment.interface';
import { Title } from '@angular/platform-browser';
import { Subscription, finalize } from 'rxjs';
import { UserService } from '../service/user.service';
import { NgForm } from '@angular/forms';

@Component({
  selector: 'fitness-program-details',
  templateUrl: './fitness-program-details.component.html',
  styleUrls: ['./fitness-program-details.component.css']
})
export class FitnessProgramDetailsComponent implements OnInit, OnDestroy {
  private subscriptions: Subscription[] = [];

  id: number = 0;
  image: number = 0;
  commentNumber: number = 0;
  reply: string = '';
  participating: boolean = false;

  fitnessProgram: FitnessProgram = {
    id: this.id,
    name: 'Nepoznato',
    price: 0,
    hidden: false
  };

  constructor(private route: ActivatedRoute,
    private title: Title,
    private fitnessProgramsService: FitnessProgramsService,
    private userService: UserService) {
    this.id = Number(this.route.snapshot.params['id']);
  }

  ngOnInit(): void {
    this.getDetails();
  }

  ngOnDestroy(): void {
    this.subscriptions.forEach(sub => sub.unsubscribe());
  }

  getDetails(): void {
    let sub = this.fitnessProgramsService.getFitnessProgram(this.id)
      .pipe(
        finalize(() => {
          this.title.setTitle(this.fitnessProgram.name);
          this.commentNumber = this.getCommentNumber(this.fitnessProgram.comments || []);
          if (this.userService.loggedIn) {
            this.checkUserParticipation();
          }
        })
      )
      .subscribe(
        {
          next: (data) => this.fitnessProgram = data,
          error: (err) => console.log(err)
        }
      );

    this.subscriptions.push(sub);
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

  postComment(form: NgForm): void {
    let body: PostCommentTemplate = {
      comment: {
        content: this.reply,
        publishedBy: {username: this.userService.currentUser?.username || ''}
      }
    };

    let sub = this.fitnessProgramsService.postComment(this.fitnessProgram.id, body)
      .subscribe(data => {
        this.fitnessProgram = data;
        this.commentNumber++;
      });
    
    this.subscriptions.push(sub);
    form.reset();
  }

  userReplied(data: FitnessProgram): void {
    this.commentNumber++;
    this.fitnessProgram = data;
  }

  checkUserParticipation(): void {
    let sub = this.userService.getFitnessProgramParticipations()
      .subscribe(data => {
        this.participating = data.find(p => p.id === this.id) ? true : false;
      });

    this.subscriptions.push(sub);
  }

  participate(): void {
    let sub = this.userService.setParticipation(this.id)
      .subscribe(data => this.participating = true);

    this.subscriptions.push(sub);
  }

}
