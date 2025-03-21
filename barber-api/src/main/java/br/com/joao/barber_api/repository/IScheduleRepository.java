package br.com.joao.barber_api.repository;

import java.time.OffsetDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.joao.barber_api.entity.Schedule_Entity;

public interface IScheduleRepository extends JpaRepository<Schedule_Entity, Long>{

    
    List<Schedule_Entity> findByStartAtGreaterThanEqualAndEndAtLessThanEqualOrderByStartAtAscEndAtAsc(
            final OffsetDateTime startAt,
            final OffsetDateTime endAt
    );

    // verificar se existe
    boolean existsByStartAtAndEndAt(final OffsetDateTime startAt, final OffsetDateTime endAt);


}
