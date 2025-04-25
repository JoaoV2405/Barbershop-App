package br.com.joao.barber_api.controller.dto;

import java.util.Set;

import br.com.joao.barber_api.entity.Client_Entity;
import br.com.joao.barber_api.entity.Schedule_Entity;

public record ClientSchedulesDTO(Long id,
String name,
String email,
String phone,
Set<Schedule_Entity> schedules){
    public ClientSchedulesDTO(Client_Entity client){
        this(client.getId(), 
        client.getName(), 
        client.getEmail(), 
        client.getPhone(),
        client.getSchedules());
    }
}
