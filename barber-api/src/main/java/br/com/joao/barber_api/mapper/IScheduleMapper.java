package br.com.joao.barber_api.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import br.com.joao.barber_api.controller.request.SaveScheduleRequest;
import br.com.joao.barber_api.controller.response.ClientScheduleAppointmentResponse;
import br.com.joao.barber_api.controller.response.SaveScheduleResponse;
import br.com.joao.barber_api.controller.response.ScheduleAppointmentMonthResponse;
import br.com.joao.barber_api.entity.Schedule_Entity;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public interface IScheduleMapper {


    @Mapping(target = "id", ignore = true)
    @Mapping(target = "client.id", source = "clientId")
    Schedule_Entity toEntity(final SaveScheduleRequest request);

    @Mapping(target = "clientId", source = "client.id")
    SaveScheduleResponse toSaveResponse(final Schedule_Entity entity);

    @Mapping(target = "scheduledAppointments", expression = "java(toClientMonthResponse(entities))")
    ScheduleAppointmentMonthResponse toMonthResponse(final int year, final int month, final List<Schedule_Entity> entities);

    List<ClientScheduleAppointmentResponse> toClientMonthResponse(final List<Schedule_Entity> entities);

    @Mapping(target = "clientId", source = "client.id")
    @Mapping(target = "clientName", source = "client.name")
    @Mapping(target = "day", expression = "java(entity.getStartAt().getDayOfMonth())")
    ClientScheduleAppointmentResponse toClientMonthResponse(final Schedule_Entity entity);

}
