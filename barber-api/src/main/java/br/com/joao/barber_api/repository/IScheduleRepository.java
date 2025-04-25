package br.com.joao.barber_api.repository;

import java.time.OffsetDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.joao.barber_api.entity.Client_Entity;
import br.com.joao.barber_api.entity.Schedule_Entity;

public interface IScheduleRepository extends JpaRepository<Schedule_Entity, Long>{

    
    List<Schedule_Entity> findByStartAtGreaterThanEqualAndEndAtLessThanEqualOrderByStartAtAscEndAtAsc(
            final OffsetDateTime startAt,
            final OffsetDateTime endAt
    );
    List<Schedule_Entity> findByClientId(Long client_id);
    // verificar se existe
    boolean existsByStartAtAndEndAt(final OffsetDateTime startAt, final OffsetDateTime endAt);


}
