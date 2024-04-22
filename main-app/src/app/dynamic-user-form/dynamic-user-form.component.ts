import { Component, OnDestroy, OnInit } from '@angular/core';
import { UserService } from '../service/user.service';
import { User } from 'src/interfaces/user.interface';
import { NgForm } from '@angular/forms';
import { Title } from '@angular/platform-browser';
import { MatSnackBar, MatSnackBarConfig } from '@angular/material/snack-bar';
import { Subscription } from 'rxjs';

@Component({
  selector: 'dynamic-user-form',
  templateUrl: './dynamic-user-form.component.html',
  styleUrls: ['./dynamic-user-form.component.css']
})
export class DynamicUserFormComponent implements OnInit, OnDestroy{
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
  private subscriptions: Subscription[] = [];
  private sbConfig: MatSnackBarConfig = {
    duration: 4000
  };

  constructor(private userService: UserService,
    private title: Title,
    private snackBar: MatSnackBar){
    if (userService.loggedIn && userService.currentUser){
      this.edit = true;
      this.user = userService.currentUser;
      this.defaultData = {...this.user};
    }
  }

  ngOnInit(): void {
    this.edit ? this.title.setTitle('Uredi profil') : this.title.setTitle('Novi profil');  
  }

  ngOnDestroy(): void {
    this.subscriptions.forEach(sub => sub.unsubscribe());
  }

  reset(): void {
    this.user = {...this.defaultData};
  }

  submit(form: NgForm): void {
    if (form.invalid){
      this.snackBar.open('Nepravilno popunjena forma', '', this.sbConfig);
      return;
    }
    
    let sub;

    if(!this.edit) {
      //create profile
      sub = this.userService.createProfile(this.user).subscribe(
        {
          next: (data) => this.snackBar
            .open(`Kreiran profil korisnika ${data.username}`, '', this.sbConfig),
          error: (err) => this.snackBar
            .open('Greška pri kreiranju korisničkog profila', '', this.sbConfig)
        }
      );

      form.reset();
    }
    else {
      sub = this.userService.updateProfile(this.user).subscribe(
        {
          next: (data) => {
            this.user = data;
            this.defaultData = {...this.user};
            this.snackBar
          .open('Uspješno izmjenjen korisnički profil', '', this.sbConfig);
          },
          error: (err) => this.snackBar
          .open('Greška pri izmjeni korisničkog profila', '', this.sbConfig)
        }
      );
    }

    this.subscriptions.push(sub);
  }

}
