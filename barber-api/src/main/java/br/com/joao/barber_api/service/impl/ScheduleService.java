package br.com.joao.barber_api.service.impl;

import org.springframework.stereotype.Repository;

import br.com.joao.barber_api.entity.Schedule_Entity;
import br.com.joao.barber_api.repository.IScheduleRepository;
import br.com.joao.barber_api.service.IScheduleService;
import br.com.joao.barber_api.service.query.IScheduleQueryService;
import lombok.AllArgsConstructor;

@Repository
@AllArgsConstructor
public class ScheduleService implements IScheduleService{
    private final IScheduleRepository repository;
    private final IScheduleQueryService queryService;
    @Override
    public Schedule_Entity save(Schedule_Entity entity) {
        queryService.verifyIfScheduleExists(entity.getStartAt(), entity.getEndAt());
        return repository.save(entity);
    }
    @Override
    public void delete(long id) {
        queryService.findById(id);
        repository.deleteById(id);
    }

    


}
