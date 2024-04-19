import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class InputService {

  private messageSource = new BehaviorSubject<string>('');
  currentMessage: Observable<string> = this.messageSource.asObservable();

  constructor() { }

  changeMessage(message: string): void {
    this.messageSource.next(message);
  }
}
