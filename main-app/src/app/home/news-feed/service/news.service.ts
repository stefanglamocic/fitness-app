import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, map } from 'rxjs';
import { News } from 'src/interfaces/news.interface';

@Injectable({
  providedIn: 'root'
})
export class NewsService {

  readonly newsUrl: string = 'http://localhost:8081/news';

  constructor(private http: HttpClient) { }

  getNews(): Observable<News[]> {
    return this.http.get(this.newsUrl, {
      responseType: 'text'
    })
      .pipe(
        map(data => new DOMParser().parseFromString(data, "text/xml")),
        map(document => this.documentToNewsList(document))
      );
  }

  private documentToNewsList(document: Document): Array<News> {
    let newsList: Array<News> = [];

    document.querySelectorAll('item').forEach(item => {
      newsList.push(
        {
          title: item.querySelector('title')?.textContent ?? '',
          description: item.querySelector('description')?.textContent ?? '',
          link: item.querySelector('link')?.textContent ?? ''
        }
      );
    });

    return newsList;
  }

}
