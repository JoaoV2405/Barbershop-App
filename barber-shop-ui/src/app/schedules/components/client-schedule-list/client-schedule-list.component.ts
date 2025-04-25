import { Component, Inject, OnDestroy, OnInit } from '@angular/core';
import { SERVICES_TOKEN } from '../../../services/service.token';
import { SnackbarManagerService } from '../../../services/snackbar-manager.service';
import { ClientsService } from '../../../services/api-client/clients/clients.service';
import { Subscription } from 'rxjs';
import { Router } from '@angular/router';
import { ClientScheduleAppointmentModel, ClientScheduleModel } from '../../schedule.models';
import { ISnackbarManagerService } from '../../../services/isnackbar-manager.service';
import { IClientService } from '../../../services/api-client/clients/iclients.service';
import { ClientModelTable } from '../../../clients/client.models';
import { ClientScheduleTableComponent } from '../../../client-schedule-table/client-schedule-table.component';
import { IScheduleService } from '../../../services/api-client/schedules/ischedules.service';
import { SchedulesService } from '../../../services/api-client/schedules/schedules.service';

@Component({
  selector: 'app-client-schedule-list',
  imports: [ClientScheduleTableComponent],
  templateUrl: './client-schedule-list.component.html',
  styleUrl: './client-schedule-list.component.scss',
  providers: [
    { provide: SERVICES_TOKEN.HTTP.CLIENT, useClass: ClientsService },
    { provide: SERVICES_TOKEN.HTTP.SCHEDULE, useClass: SchedulesService },
    { provide: SERVICES_TOKEN.SNACKBAR, useClass: SnackbarManagerService }
  ]
})


export class ClientScheduleList implements OnInit, OnDestroy {

  private httpSubscriptions: Subscription[] = []


  schedules: ClientScheduleModel[] = []

  constructor(
    @Inject(SERVICES_TOKEN.HTTP.CLIENT) private readonly httpService: IClientService,
    @Inject(SERVICES_TOKEN.HTTP.SCHEDULE) private readonly httpScheduleService: IScheduleService,
    @Inject(SERVICES_TOKEN.SNACKBAR) private readonly snackBarManager: ISnackbarManagerService,
    private readonly router: Router
  ) { }

  ngOnInit(): void {
    this.httpSubscriptions.push(this.httpService.findSchedulesById().subscribe(data => this.schedules = data))
  }
  ngOnDestroy(): void {
    this.httpSubscriptions.forEach(s => s.unsubscribe())
  }


  delete(client: ClientScheduleModel) {
    this.httpSubscriptions.push(
      this.httpScheduleService.delete(client.id).subscribe(_ => this.snackBarManager.show(`Reserva foi excluida com sucesso`))
    )
  }

}