import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { Observable, tap } from 'rxjs';
import { User } from 'src/interfaces/user.interface';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  private readonly baseUrl: string = 'http://localhost:8081/api/users';
  currentUser: User | null = null;
  loggedIn: boolean = false;

  constructor(private http: HttpClient,
    private router: Router) { }

  login(credentials: {username: string | null, password: string | null}): Observable<User> {
    return this.http.post<User>(`${this.baseUrl}/login`, credentials)
      .pipe(
        tap(value => {
          this.currentUser = value;
          if(this.currentUser)
            this.loggedIn = true;
        })
      );
  }

  logout(): void {
    this.loggedIn = false;
    this.currentUser = null;
    this.router.navigate(['/']);
  }

}
