import { Component, OnDestroy, OnInit } from '@angular/core';
import { UserService } from '../service/user.service';
import { Subscription, config, filter, finalize } from 'rxjs';
import { User } from 'src/interfaces/user.interface';
import { Router } from '@angular/router';
import { MatSnackBar, MatSnackBarConfig } from '@angular/material/snack-bar';

@Component({
  selector: 'login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit, OnDestroy{
  private subscriptions: Subscription[] = [];

  currentUser!: User | null;

  userCredentials: {username: string | null, password: string | null} = {
    username: null,
    password: null
  };

  constructor(private userService: UserService,
    private router: Router,
    private snackBar: MatSnackBar) {}

  ngOnInit(): void {}

  ngOnDestroy(): void {
    this.subscriptions.forEach(s => s.unsubscribe());
  }

  login(): void {
    let subscription = this.userService.login(this.userCredentials)
      .pipe(
        finalize(() => {
          if (this.currentUser)
            this.router.navigate(['/']);
          else
            this.snackBar
              .open('Pogrešni kredencijali, pokušajte ponovo.', '', {
                duration: 4000
              });
        })
      )
      .subscribe(data => this.currentUser = data);

    this.subscriptions.push(subscription);
  }

}
