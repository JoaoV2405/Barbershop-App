import { Routes } from '@angular/router';
import { SchedulesMonthComponent } from './schedules/schedules-month/schedules-month.component';
import { ListClientsComponent } from './clients/list-clients/list-clients.component';
import { NewClientComponent } from './clients/new-client/new-client.component';
import { EditClientComponent } from './clients/edit-client/edit-client.component';
import { LoginComponent } from './auth/login/login.component';
import { guardGuard } from './guard.guard';
import { ClientScheduleList } from './schedules/components/client-schedule-list/client-schedule-list.component';

export const routes: Routes = [
    { path: 'clients/edit-client/:id', component: EditClientComponent, data: { title: 'Atualizar Cliente' }, canActivate: [guardGuard]},
    { path: 'clients/new-client', component: NewClientComponent, data: { title: 'Cadastrar Cliente' }, canActivate: [guardGuard] },
    { path: 'clients/list', component: ListClientsComponent, data: { title: 'Clientes Cadastrados' }, canActivate: [guardGuard] },
    { path: 'clients/schedules', component: ClientScheduleList, data: { title: 'Reservas do Cliente' }, canActivate: [guardGuard] },
    { path: 'schedules/month', component: SchedulesMonthComponent, data: { title: 'Agendamentos' }, canActivate: [guardGuard] },
    { path: 'login', component: LoginComponent ,data: { title: 'Login' } },
    { path: '**', redirectTo: 'login'},

];
