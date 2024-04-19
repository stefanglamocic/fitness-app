import { HttpClient } from '@angular/common/http';
import { Injectable, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { User } from 'src/interfaces/user.interface';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  private readonly baseUrl: string = 'http://localhost:8081/api/users';

  constructor(private http: HttpClient) { }

  login(credentials: {username: string | null, password: string | null}): Observable<User> {
    return this.http.post<User>(`${this.baseUrl}/login`, credentials);
  }



}
