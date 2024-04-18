import { Component, Input } from '@angular/core';
import { CommentInterface } from 'src/interfaces/comment.interface';

@Component({
  selector: 'comment',
  templateUrl: './comment.component.html',
  styleUrls: ['./comment.component.css']
})
export class CommentComponent {

  @Input()
  comment!: CommentInterface;

  constructor() {}

}
