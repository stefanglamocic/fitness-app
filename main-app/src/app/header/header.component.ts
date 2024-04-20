import { Component, OnDestroy, OnInit } from '@angular/core';
import { NavigationEnd, Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { InputService } from '../service/input.service';
import { UserService } from '../service/user.service';

@Component({
  selector: 'header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit, OnDestroy{
  private subscritpion!: Subscription;
  
  showSearchBar: boolean = false;

  constructor(private router: Router,
    private inputService: InputService,
    private userService: UserService) {}

  ngOnInit(): void {
    this.router.events.subscribe(val => {
      if(val instanceof NavigationEnd) {
        this.showSearchBar = (val.url == '/home') || (val.url == '/'); 
      }
    });
  }

  getInput(event: any): void {
    this.inputService.changeMessage(event.target.value);
  }

  ngOnDestroy(): void {
    if(this.subscritpion)
      this.subscritpion.unsubscribe();
  }

  logout(): void {
    this.userService.logout();
  }

  userLoggedIn(): boolean {
    return this.userService.loggedIn;
  }

  getUsername(): string {
    return this.userService.currentUser?.username || '';
  }

}
