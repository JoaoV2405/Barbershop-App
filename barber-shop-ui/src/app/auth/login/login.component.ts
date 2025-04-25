import { Component, Inject, OnInit } from '@angular/core';
import { FormComponent } from "../form/form.component";
import { ClientModelForm, ClientModelRegisterForm, LoginModelForm } from '../../clients/client.models';
import { SERVICES_TOKEN } from '../../services/service.token';
import { IClientService } from '../../services/api-client/clients/iclients.service';
import { ISnackbarManagerService } from '../../services/isnackbar-manager.service';
import { Router } from '@angular/router';
import { ClientsService } from '../../services/api-client/clients/clients.service';
import { SnackbarManagerService } from '../../services/snackbar-manager.service';
import { LoginRequest } from '../../services/api-client/clients/client.models';
import { AuthService } from '../../services/auth-service/auth.service';

@Component({
  selector: 'app-login',
  imports: [FormComponent],
  templateUrl: './login.component.html',
  styleUrl: './login.component.scss',
  providers: [{ provide: SERVICES_TOKEN.HTTP.CLIENT, useClass: ClientsService },
        { provide: SERVICES_TOKEN.SNACKBAR, useClass: SnackbarManagerService }]
})
export class LoginComponent implements OnInit{

  constructor(
    @Inject(SERVICES_TOKEN.HTTP.CLIENT) private readonly httpService: IClientService,
    @Inject(SERVICES_TOKEN.SNACKBAR) private readonly snackBarManager: ISnackbarManagerService,
    private readonly router: Router,
    private readonly authService: AuthService // <-- Adicionado aqui

  ){}
  ngOnInit(): void {
    console.log('')
  }
  onLoginEvent(value:LoginModelForm) {
    const {login, ...request} = value

    this.httpService.login(value).subscribe(response => {
      if(response.token) {
        this.authService.setToken(response.token)
        this.snackBarManager.show("Login realizado com sucesso")
        this.router.navigate(['clients/list'])
      }
      else{
        this.snackBarManager.show("Token inválido")
      }
      
    });
  }
  onRegisterEvent(value:ClientModelRegisterForm) {
    const {name, ...request} = value
    this.httpService.register(value).subscribe(response => {
      if(response.token) {
      this.authService.setToken(response.token)
      this.snackBarManager.show("Login realizado com sucesso")
      this.router.navigate(['clients/list'])
    }});
  }
}
