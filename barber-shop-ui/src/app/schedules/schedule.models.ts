export interface ScheduleAppointementMonthModel {
    year: number
    month: number
    scheduledAppointments: ClientScheduleAppointmentModel[]
}

export interface ClientScheduleAppointmentModel {
    id: number
    day: number
    startAt: Date
    endAt: Date
    clientId: number
    clientName: string
}
export interface ClientScheduleModel{
    id: number
    day: number
    startAt: Date
    endAt: Date
}

export interface SaveScheduleModel {
    startAt?: Date
    endAt?: Date
    clientId?: number
}

export interface SelectClientModel {
    id: number
    name: string
}