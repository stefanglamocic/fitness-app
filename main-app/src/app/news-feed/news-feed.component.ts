import { Component, OnDestroy, OnInit } from '@angular/core';
import { NewsService } from './service/news.service';
import { Subscription, finalize } from 'rxjs';
import { News } from 'src/interfaces/news.interface';

@Component({
  selector: 'news-feed',
  templateUrl: './news-feed.component.html',
  styleUrls: ['./news-feed.component.css']
})
export class NewsFeedComponent implements OnInit, OnDestroy {
  readonly WAIT_TIME: number = 9000;
  title!: string;
  description!: string;
  link!: string;

  news!: Array<News>;
  index: number = 0;
  showLink: boolean = true;

  subscription!: Subscription;

  constructor(private newsService: NewsService) { }

  ngOnInit(): void {
    this.getData();
  }

  ngOnDestroy(): void {
    if (this.subscription) {
      this.subscription.unsubscribe();
    }
  }

  getData(): void {
    this.subscription = this.newsService.getNews().pipe(
      finalize(() => {
        this.setNewsToDisplay();
        this.setNewsCycle();
      })
    )
      .subscribe({
        next: (data) => {
          this.news = data;
        },
        error: (err) => {
          this.title = 'Vijesti trenutno nisu dostupne';
          this.description = '';
          this.showLink = false;
        }
      });
  }

  private setNewsCycle(): void {
    setInterval(() => this.setNewsToDisplay(), this.WAIT_TIME);
  }

  private setNewsToDisplay(): void {
    this.title = this.news[this.index].title;
    this.description = this.news[this.index].description;
    this.link = this.news[this.index].link;

    this.index = (this.index + 1) % this.news.length;
  }

}
