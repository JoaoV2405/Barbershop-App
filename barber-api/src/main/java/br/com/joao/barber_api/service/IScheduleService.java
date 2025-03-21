package br.com.joao.barber_api.service;

import br.com.joao.barber_api.entity.Schedule_Entity;

public interface IScheduleService {
    Schedule_Entity save(final Schedule_Entity entity);

    void delete(final long id);
}
