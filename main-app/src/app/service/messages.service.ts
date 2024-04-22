import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { MessageInterface } from 'src/interfaces/message.interface';

@Injectable({
  providedIn: 'root'
})
export class MessagesService {
  private readonly url: string = 'http://localhost:8081/api/messages';

  constructor(private http: HttpClient) { }

  sendMessage(message: MessageInterface): Observable<MessageInterface> {
    return this.http.post<MessageInterface>(this.url, message);
  }

  openMessage(message: MessageInterface): Observable<any> {
    return this.http.patch<any>(this.url, message);
  }
}
