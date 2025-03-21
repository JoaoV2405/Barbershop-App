package br.com.joao.barber_api.service.impl;

import org.springframework.stereotype.Repository;

import br.com.joao.barber_api.entity.Client_Entity;
import br.com.joao.barber_api.repository.IClientRepository;
import br.com.joao.barber_api.service.IClientService;
import br.com.joao.barber_api.service.query.IClientQueryService;
import lombok.AllArgsConstructor;

@Repository
@AllArgsConstructor
public class ClientService implements IClientService{
    private final IClientRepository repository;
    private final IClientQueryService queryService;
    @Override
    public Client_Entity save(Client_Entity entity) {
        queryService.verifyEmail(entity.getEmail());
        queryService.verifyPhone(entity.getPhone());
        return repository.save(entity);
    }
    @Override
    public Client_Entity update(Client_Entity entity) {
        // TODO Auto-generated method stub
        queryService.verifyEmail(entity.getId(),entity.getEmail());
        queryService.verifyPhone(entity.getId(),entity.getPhone());

        var stored = queryService.findById(entity.getId());
        stored.setId(entity.getId());
        stored.setName(entity.getName());
        stored.setEmail(entity.getEmail());
        stored.setPhone(entity.getPhone());
        return repository.save(stored);
    }
    @Override
    public void delete(Long id) {
        queryService.findById(id);
        repository.deleteById(id);
    }
}
