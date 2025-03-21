package br.com.joao.barber_api.service.query;

import java.util.List;

import br.com.joao.barber_api.entity.Client_Entity;

public interface IClientQueryService {
    Client_Entity findById(final long id);

    List<Client_Entity> list();

    void verifyPhone(final String phone);

    void verifyPhone(final long id,final String phone);

    void verifyEmail(final String email);

    void verifyEmail(final long id,final String email);
}
