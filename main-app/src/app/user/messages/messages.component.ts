import { Component, OnDestroy, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Subscription } from 'rxjs';
import { MessagesService } from 'src/app/service/messages.service';
import { UserService } from 'src/app/service/user.service';
import { MessageInterface } from 'src/interfaces/message.interface';

@Component({
  selector: 'messages',
  templateUrl: './messages.component.html',
  styleUrls: ['./messages.component.css']
})
export class MessagesComponent implements OnInit, OnDestroy{
  private subscriptions: Subscription[] = [];

  inbox: Array<MessageInterface> = [];
  messageToSend: MessageInterface;

  constructor(private userService: UserService,
    private messagesService: MessagesService,
    private snackBar: MatSnackBar){
    this.messageToSend = {
      sender: { username: userService.currentUser?.username || '' },
      receiver: { username: ''},
      content: ''
    };
  }

  ngOnInit(): void {
    this.getInbox();
  }

  getInbox(): void {
    let sub = this.userService.getInbox().subscribe(
      {
        next: (data) => this.inbox = data
      }
    );
    this.subscriptions.push(sub);
  }

  ngOnDestroy(): void {
    this.subscriptions.forEach(sub => sub.unsubscribe());
  }

  openMessage(message: MessageInterface): void {
    let sub = this.messagesService.openMessage(message).subscribe();
    this.subscriptions.push(sub);
  }

  sendMessage(form: NgForm): void {
    let sub = this.messagesService.sendMessage(this.messageToSend).subscribe(
      {
        next: (data) => this.snackBar.open('Poruka poslata.', '', {duration: 4000}),
        error: (err) => this.snackBar.open('Greška pri slanju poruke.', '', {duration: 4000})
      }
    );
    this.subscriptions.push(sub);
    form.reset();
  }

}
