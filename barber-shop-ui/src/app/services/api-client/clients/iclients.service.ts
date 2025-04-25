import { Observable } from "rxjs";
import { DetailClientResponse, ListClientResponse, LoginRequest, LoginResponse, RegisterRequest, RegisterResponse, SaveClientRequest, SaveClientResponse, UpdateClientRequest, UpdateClientResponse } from "./client.models";
import { ClientModelRegisterForm } from "../../../clients/client.models";
import { ClientScheduleAppointmentModel, ClientScheduleModel } from "../../../schedules/schedule.models";

export interface IClientService{
    register(request: RegisterRequest): Observable<RegisterResponse>;
    login(request:LoginRequest): Observable<LoginResponse>
    save(request:SaveClientRequest): Observable<SaveClientResponse>
    update(id:number,request:UpdateClientRequest): Observable<UpdateClientResponse>
    delete(id:number): Observable<void>
    list(): Observable<ListClientResponse[]>
    findById(id:number): Observable<DetailClientResponse> 
    findSchedulesById(): Observable<ClientScheduleModel[]> 
    
}