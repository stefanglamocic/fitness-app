import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Category } from 'src/interfaces/category.interface';
import { FitnessProgram } from 'src/interfaces/fitness-program.interface';
import { Instructor } from 'src/interfaces/instructor.interface';

@Injectable({
  providedIn: 'root'
})
export class FitnessProgramsService {
  private readonly fitnessProgramsUrl: string = 'http://localhost:8081/api/fitness-programs';
  private readonly categoriesUrl: string = 'http://localhost:8081/api/categories';
  private readonly instructorsUrl: string = 'http://localhost:8081/api/instructors';
  private readonly imagesUrl: string = 'http://localhost:8081/api/images';

  constructor(private http: HttpClient) { }

  getAll(offset: number = 0, items: number = 10): Observable<FitnessProgram[]> {
    return this.http
      .get<FitnessProgram[]>(`${this.fitnessProgramsUrl}?offset=${offset}&items=${items}`);
  }

  getFitnessProgram(id: number): Observable<FitnessProgram> {
    return this.http.get<FitnessProgram>(`${this.fitnessProgramsUrl}/${id}`);
  }

  addFitnessProgram(body: FitnessProgram): Observable<FitnessProgram> {
    return this.http.post<FitnessProgram>(`${this.fitnessProgramsUrl}/add`, body);
  }

  removeFitnessProgram(id: number): Observable<any>{
    return this.http.delete(`${this.fitnessProgramsUrl}/${id}`);
  }

  postComment(id: number, body: PostCommentTemplate): Observable<FitnessProgram> {
    return this.http.post<FitnessProgram>(`${this.fitnessProgramsUrl}/${id}/comment`, 
      body);
  }

  getCategories(): Observable<Category[]> {
    return this.http.get<Category[]>(this.categoriesUrl);
  }

  getInstructors(): Observable<Instructor[]> {
    return this.http.get<Instructor[]>(this.instructorsUrl);
  }

  uploadImages(images: {path: string}[]): Observable<any> {
    return this.http.post<any>(this.imagesUrl, images);
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