package br.com.joao.barber_api.controller.dto;
import br.com.joao.barber_api.entity.Client_Entity;
import lombok.Builder;

@Builder
public record ClientDTO(Long id,
String name,
String email,
String phone){
    public ClientDTO(Client_Entity client){
        this(client.getId(), 
        client.getName(), 
        client.getEmail(), 
        client.getPhone());
    }

}

