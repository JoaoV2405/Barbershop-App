package br.com.joao.barber_api.service.query;

import java.time.OffsetDateTime;
import java.util.List;

import br.com.joao.barber_api.entity.Schedule_Entity;

public interface IScheduleQueryService {
    Schedule_Entity findById(final Long id);

    List<Schedule_Entity> findByClientId(final Long id);

    List<Schedule_Entity> findInMonth(final OffsetDateTime startAt, final OffsetDateTime endAt);

    void verifyIfScheduleExists(final OffsetDateTime startAt, final OffsetDateTime endAt);
}
