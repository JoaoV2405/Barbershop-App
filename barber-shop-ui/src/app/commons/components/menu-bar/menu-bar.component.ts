import { Component } from '@angular/core';
import { MatMenuModule } from '@angular/material/menu';
import { MatButtonModule } from '@angular/material/button';
import { Router } from '@angular/router';
import { AuthService } from '../../../services/auth-service/auth.service';
@Component({
  selector: 'app-menu-bar',
  imports: [MatMenuModule,MatButtonModule],
  templateUrl: './menu-bar.component.html',
  styleUrl: './menu-bar.component.scss'
})
export class MenuBarComponent {
  constructor(private readonly router: Router,
    private readonly auth: AuthService
  ){}
  navigateTo(path:string) {
    this.router.navigate([path]);
}
  logout(path:string) {
    this.auth.clearToken();
    this.router.navigate([path]);
}
  
}
