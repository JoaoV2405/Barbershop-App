package br.com.joao.barber_api.service;

import br.com.joao.barber_api.controller.dto.ClientDTO;
import br.com.joao.barber_api.controller.dto.ClientLoginDTO;
import br.com.joao.barber_api.controller.dto.CredentialsDTO;
import br.com.joao.barber_api.entity.Client_Entity;

public interface IClientService {

    Client_Entity save(final Client_Entity entity);
    Client_Entity register(final Client_Entity entity);
    Client_Entity update(final Long id,final Client_Entity entity);
    void delete(final Long id);
    ClientLoginDTO login(CredentialsDTO credentialsDTO);
}
