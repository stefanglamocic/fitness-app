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

  getAll(): Observable<FitnessProgram[]> {
    return this.http.get<FitnessProgram[]>(this.fitnessProgramsUrl);
  }

  getFitnessProgram(id: number): Observable<FitnessProgram> {
    return this.http.get<FitnessProgram>(`${this.fitnessProgramsUrl}/${id}`);
  }

  removeFitnessProgram(id: number): Observable<any>{
    return this.http.delete(`${this.fitnessProgramsUrl}/${id}`);
  }
}
