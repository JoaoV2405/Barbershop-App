import { Component, OnDestroy, OnInit } from '@angular/core';
import { ActivatedRoute, NavigationEnd, Router, RouterOutlet } from '@angular/router';
import { CardHeaderComponent } from "./commons/components/card-header/card-header.component";
import { filter, map, Subscription } from 'rxjs';
import { MenuBarComponent } from "./commons/components/menu-bar/menu-bar.component";
import { LoginComponent } from "./auth/login/login.component";
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-root',
  standalone:true,
  imports: [RouterOutlet, CardHeaderComponent, LoginComponent, CommonModule],
  templateUrl: './app.component.html',
  styleUrl: './app.component.scss'
})
export class AppComponent implements OnInit, OnDestroy{
  title:string = 'barber-shop-ui';
  private routeSubscription?: Subscription;
  constructor(private readonly router: Router,
    private readonly activatedroute: ActivatedRoute
  ){}
  get showHeader(): boolean {
    return !this.router.url.includes('login');
  }
  ngOnInit(): void {
    this.routeSubscription = this.router.events.pipe(
      filter(event => event instanceof NavigationEnd),
      map(() => this.getRouteTitle(this.activatedroute))
    ).subscribe(title => {
      this.title = title
    })
  }
  ngOnDestroy(): void {
    this.routeSubscription?.unsubscribe();
  }

  private getRouteTitle(route: ActivatedRoute): string {
    let child = route;
    while (child.firstChild) {
      child = child.firstChild;
    }
    return child.snapshot.data['title'] || 'Default Title';
  }
}
