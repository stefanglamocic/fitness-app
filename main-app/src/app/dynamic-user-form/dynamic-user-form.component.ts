import { Component, OnInit } from '@angular/core';
import { UserService } from '../service/user.service';
import { User } from 'src/interfaces/user.interface';
import { NgForm } from '@angular/forms';
import { Title } from '@angular/platform-browser';

@Component({
  selector: 'dynamic-user-form',
  templateUrl: './dynamic-user-form.component.html',
  styleUrls: ['./dynamic-user-form.component.css']
})
export class DynamicUserFormComponent implements OnInit{
  edit: boolean = false;
  user!: User | null;

  constructor(private userService: UserService,
    private title: Title){
    if (userService.loggedIn){
      this.edit = true;
      this.user = userService.currentUser;
    }
  }

  ngOnInit(): void {
    if (this.edit)
      this.title.setTitle('Uredi profil');
    else
      this.title.setTitle('Novi profil');
  }

  submit(form: NgForm): void {}

}
