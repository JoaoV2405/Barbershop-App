package br.com.joao.barber_api.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import br.com.joao.barber_api.controller.request.SaveClientRequest;
import br.com.joao.barber_api.controller.request.UpdateClientRequest;
import br.com.joao.barber_api.controller.response.ClientDetailResponse;
import br.com.joao.barber_api.controller.response.ListClientResponse;
import br.com.joao.barber_api.controller.response.SaveClientResponse;
import br.com.joao.barber_api.controller.response.UpdateClientResponse;
import br.com.joao.barber_api.entity.Client_Entity;
import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public interface IClientMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "schedules", ignore = true)
    Client_Entity toEntity(final SaveClientRequest request);

    SaveClientResponse toSaveResponse(final Client_Entity entity);

    @Mapping(target = "schedules", ignore = true)
    Client_Entity toEntity(final long id, final UpdateClientRequest request);

    UpdateClientResponse toUpdateResponse(final Client_Entity entity);

    ClientDetailResponse toDetailResponse(final Client_Entity entity);

    List<ListClientResponse> toListResponse(final List<Client_Entity> entities);
}
