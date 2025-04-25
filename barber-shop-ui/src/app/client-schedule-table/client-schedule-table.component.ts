import { Component, Input, ViewChild, Output, EventEmitter, Inject, SimpleChanges } from "@angular/core";
import { MatPaginator, MatPaginatorModule } from "@angular/material/paginator";
import { MatTableDataSource, MatTableModule } from "@angular/material/table";
import { Subscription } from "rxjs";
import { ClientModelTable } from "../clients/client.models";
import { YesNoDialogComponent } from "../commons/components/yes-no-dialog/yes-no-dialog.component";
import { IDialogManagerService } from "../services/idialog-manager.service";
import { SERVICES_TOKEN } from "../services/service.token";
import { DialogManagerService } from "../services/dialog-manager.service";
import { MatButtonModule } from "@angular/material/button";
import { MatIconModule } from "@angular/material/icon";
import { MatTooltipModule } from "@angular/material/tooltip";
import { ClientScheduleModel } from "../schedules/schedule.models";


@Component({
  selector: 'app-client-schedule-table',
  imports: [[MatTableModule, MatButtonModule, MatIconModule, MatPaginatorModule, MatTooltipModule]],
  templateUrl: './client-schedule-table.component.html',
  styleUrl: './client-schedule-table.component.scss',
  providers: [
      { provide: SERVICES_TOKEN.DIALOG, useClass: DialogManagerService },]
})
export class ClientScheduleTableComponent {
  @Input() schedules: ClientScheduleModel[] = []

  dataSource!: MatTableDataSource<ClientScheduleModel>


  displayedColumns: string[] = ['data', 'horario', 'actions']

  private dialogManagerServiceSubscriptions?: Subscription

  @Output() onConfirmDelete = new EventEmitter<ClientScheduleModel>()


  constructor(
    @Inject(SERVICES_TOKEN.DIALOG) private readonly dialogManagerService: IDialogManagerService,
  ) { }

  
  ngOnChanges(changes: SimpleChanges): void {
    if (changes['schedules'] && this.schedules) {
      this.dataSource = new MatTableDataSource<ClientScheduleModel>(this.schedules)
    }
  }
  ngOnDestroy(): void {
    if (this.dialogManagerServiceSubscriptions) {
      this.dialogManagerServiceSubscriptions.unsubscribe()
    }
  }

  getData(data:Date): String{
    const date = new Date(data)
    const dia = date.getDate().toString().padStart(2, '0')
    const mes = (date.getMonth() + 1).toString().padStart(2, '0') 
    const ano = date.getFullYear()
    return `${dia}/${mes}/${ano}`
  }
  getHora(data:Date){
    const date = new Date(data)
    const horas = date.getHours().toString().padStart(2, '0')
    const minutos = date.getMinutes().toString().padStart(2, '0')
    return `${horas}:${minutos}`
  }

  delete(client: ClientScheduleModel) {
    this.dialogManagerService.showYesNoDialog(
      YesNoDialogComponent,
      { title: 'Exclusão de reserva', content: `Confirma a exclusão da reserva` }
    )
      .subscribe(result => {
        if (result) {
          this.onConfirmDelete.emit(client)
          const updatedList = this.dataSource.data.filter(c => c.id !== client.id)
          this.dataSource = new MatTableDataSource<ClientScheduleModel>(updatedList)
        }
      })
  }

}
