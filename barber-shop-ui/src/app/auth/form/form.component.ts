import { Component, EventEmitter, Inject, Input, OnInit, Output } from '@angular/core';
import { SERVICES_TOKEN } from '../../services/service.token';
import { IClientService } from '../../services/api-client/clients/iclients.service';
import { ISnackbarManagerService } from '../../services/isnackbar-manager.service';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { FormsModule } from '@angular/forms';
import { ClientsService } from '../../services/api-client/clients/clients.service';
import { SnackbarManagerService } from '../../services/snackbar-manager.service';
import { MatFormField, MatInputModule, MatLabel } from '@angular/material/input';
import { MatButton, MatButtonModule } from '@angular/material/button';
import { ClientModelForm, ClientModelRegisterForm, LoginModelForm } from '../../clients/client.models';
import { CommonModule, NgClass } from '@angular/common';
import { NgxMaskDirective } from 'ngx-mask';
import { MatFormFieldModule } from '@angular/material/form-field';


@Component({
  selector: 'app-form',
  imports: [FormsModule,
    MatInputModule,
    MatFormFieldModule,
    MatButtonModule,
    NgxMaskDirective,
  CommonModule],
  templateUrl: './form.component.html',
  styleUrl: './form.component.scss',
  providers: [{ provide: SERVICES_TOKEN.HTTP.CLIENT, useClass: ClientsService },
      { provide: SERVICES_TOKEN.SNACKBAR, useClass: SnackbarManagerService }]
})


export class FormComponent{

  active:string = 'login'

  @Output() onSubmitLoginEvent = new EventEmitter<LoginModelForm>();
  @Output() onSubmitRegisterEvent = new EventEmitter<ClientModelRegisterForm>();
  
  @Input()
  login: LoginModelForm = {id:0, login:'', password:''}
  
  @Input()
  client: ClientModelRegisterForm = {id:0, name:'', email:'', phone:'', password:''}
 

  onSubmitLogin(){
    this.onSubmitLoginEvent.emit(this.login)
  }

  onSubmitRegister() {
    this.onSubmitRegisterEvent.emit(this.client)
    }
  onRegisterTab() {
    this.active = 'register';
    }
  onLoginTab() {
    this.active = 'login';
    }

}
