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
  user: User = {
    name: '',
    surname: '',
    username: '',
    city: '',
    mail: '',
    activated: false,
    userType: 'F'
  };
  private defaultData: User = {...this.user};

  constructor(private userService: UserService,
    private title: Title){
    if (userService.loggedIn && userService.currentUser){
      this.edit = true;
      this.user = userService.currentUser;
      this.defaultData = {...this.user};
    }
  }

  ngOnInit(): void {
    this.edit ? this.title.setTitle('Uredi profil') : this.title.setTitle('Novi profil');  
  }

  reset(): void {
    this.user = {...this.defaultData};
  }

  submit(form: NgForm): void {
    console.log(this.user);
  }

}
