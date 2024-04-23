import { Component, EventEmitter, Input, OnDestroy, Output } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Subscription } from 'rxjs';
import { FitnessProgramsService, PostCommentTemplate } from 'src/app/home/fitness-programs-view/service/fitness-programs.service';
import { UserService } from 'src/app/service/user.service';
import { CommentInterface } from 'src/interfaces/comment.interface';
import { FitnessProgram } from 'src/interfaces/fitness-program.interface';

@Component({
  selector: 'comment',
  templateUrl: './comment.component.html',
  styleUrls: ['./comment.component.css']
})
export class CommentComponent implements OnDestroy{
  private subscriptions: Subscription[] = [];

  showReplyForm: boolean = false;
  replyContent: string = '';

  @Input()
  comment!: CommentInterface;
  @Input()
  replyAbility: boolean = false;
  @Input()
  fitnessProgramId: number = 0;

  @Output()
  replied: EventEmitter<FitnessProgram> = new EventEmitter<FitnessProgram>();


  constructor(private userService: UserService,
      private fitnessProgramService: FitnessProgramsService) {}

  ngOnDestroy(): void {
    this.subscriptions.forEach(sub => sub.unsubscribe());
  }

  toggleReplyForm(): void {
    this.showReplyForm = !this.showReplyForm;
  }

  reply(form: NgForm): void {
    let body: PostCommentTemplate = {
      comment: {
        publishedBy: {username: this.userService.currentUser?.username || ''},
        content: this.replyContent
      },
      replyTo: {
        publishedBy: {username: this.comment.publishedBy.username},
        published: this.comment.published
      }
    };

    let sub = this.fitnessProgramService.postComment(this.fitnessProgramId, body)
      .subscribe(data => this.replied.emit(data));

    this.subscriptions.push(sub);

    form.reset();
  }

}
