import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { Observable, tap } from 'rxjs';
import { FitnessProgram } from 'src/interfaces/fitness-program.interface';
import { User } from 'src/interfaces/user.interface';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  private readonly baseUrl: string = 'http://localhost:8081/api/users';
  currentUser: User | null = null;
  loggedIn: boolean = false;

  constructor(private http: HttpClient) {
    let storeValue = localStorage.getItem('user');
    if (storeValue) {
      this.loggedIn = true;
      this.currentUser = JSON.parse(storeValue);
    }
  }

  login(credentials: {username: string | null, password: string | null}): Observable<User> {
    return this.http.post<User>(`${this.baseUrl}/login`, credentials)
      .pipe(
        tap(value => {
          this.currentUser = value;
          if(this.currentUser) {
            this.loggedIn = true;
            localStorage.setItem('user', JSON.stringify(this.currentUser));
          }
        })
      );
  }

  logout(): void {
    this.loggedIn = false;
    this.currentUser = null;
    localStorage.removeItem('user');
  }

  createProfile(user: User): Observable<User> {
    return this.http.post<User>(`${this.baseUrl}/add`, user);
  }

  updateProfile(user: User): Observable<User> {
    return this.http.put<User>(`${this.baseUrl}/update`, user);
  }

  getCreatedFitnessPrograms(): Observable<FitnessProgram[]> {
    return this.http
      .get<FitnessProgram[]>(`${this.baseUrl}/${this.currentUser?.username}/created-fitness-programs`);
  }

  getFitnessProgramParticipations(): Observable<FitnessProgram[]> {
    return this.http
      .get<FitnessProgram[]>(`${this.baseUrl}/${this.currentUser?.username}/fitness-program-participations`);
  }

}
