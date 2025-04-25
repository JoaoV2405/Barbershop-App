package br.com.joao.barber_api.mapper;

import br.com.joao.barber_api.controller.dto.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import br.com.joao.barber_api.entity.Client_Entity;
import br.com.joao.barber_api.entity.Schedule_Entity;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

import java.util.List;

@Mapper(componentModel = SPRING)
public interface ClientMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "schedules", ignore = true)
    Client_Entity toEntity(final ClientDTO dto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "schedules", ignore = true)
    Client_Entity ClientRegisterDTOtoEntity(final ClientRegisterDTO dto);

    @Mapping(target="token", ignore = true)
    ClientRegisterDTO Client_EntityToClientRegisterDTO(Client_Entity client);

    ClientLoginDTO ClientRegisterDTOToClientLoginDTO(ClientRegisterDTO client);

    List<ClientDTO> Client_EntityToClientDTOList(List<Client_Entity> clients);
    ClientDTO Client_EntityToClientDTO(Client_Entity client);

    ClientLoginDTO Client_EntityToClientLoginDTO(Client_Entity client);


    @Mapping(target = "id", ignore = true)
    @Mapping(target = "clientId", source = "client.id")
    @Mapping(target = "clientName", source = "client.name")
    @Mapping(target = "day", expression = "java(schedule.getStartAt().getDayOfMonth())")
    ScheduleDetailDTO Schedule_EntityToScheduleDTO(Schedule_Entity schedule);


    


}
