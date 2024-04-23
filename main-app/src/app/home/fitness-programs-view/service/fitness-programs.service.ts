import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { FitnessProgram } from 'src/interfaces/fitness-program.interface';

@Injectable({
  providedIn: 'root'
})
export class FitnessProgramsService {
  readonly fitnessProgramsUrl: string = 'http://localhost:8081/api/fitness-programs';

  constructor(private http: HttpClient) { }

  getAll(offset: number = 0, items: number = 10): Observable<FitnessProgram[]> {
    return this.http
      .get<FitnessProgram[]>(`${this.fitnessProgramsUrl}?offset=${offset}&items=${items}`);
  }

  getFitnessProgram(id: number): Observable<FitnessProgram> {
    return this.http.get<FitnessProgram>(`${this.fitnessProgramsUrl}/${id}`);
  }

  removeFitnessProgram(id: number): Observable<any>{
    return this.http.delete(`${this.fitnessProgramsUrl}/${id}`);
  }

  postComment(id: number, body: PostCommentTemplate): Observable<FitnessProgram> {
    return this.http.post<FitnessProgram>(`${this.fitnessProgramsUrl}/${id}/comment`, 
      body);
  }

}

export interface PostCommentTemplate {
  comment: {
    publishedBy: {username: string},
    content: string
  },
  replyTo?: {
    publishedBy: {username: string},
    published: string
  }
}
