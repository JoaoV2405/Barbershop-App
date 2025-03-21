package br.com.joao.barber_api.service;

import br.com.joao.barber_api.entity.Client_Entity;

public interface IClientService {

    Client_Entity save(final Client_Entity entity);
    Client_Entity update(final Client_Entity entity);
    void delete(final Long id);
}
