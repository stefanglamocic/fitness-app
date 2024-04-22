import { Component, Input } from '@angular/core';
import { UserService } from 'src/app/service/user.service';
import { CommentInterface } from 'src/interfaces/comment.interface';

@Component({
  selector: 'comment',
  templateUrl: './comment.component.html',
  styleUrls: ['./comment.component.css']
})
export class CommentComponent {
  showReplyForm: boolean = false;

  @Input()
  comment!: CommentInterface;
  @Input()
  replyAbility: boolean = false;

  constructor(private userService: UserService) {}

  toggleReplyForm(): void {
    this.showReplyForm = !this.showReplyForm;
  }

}
