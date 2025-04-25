package br.com.joao.barber_api.service.impl;

import br.com.joao.barber_api.controller.dto.ClientLoginDTO;
import br.com.joao.barber_api.controller.dto.CredentialsDTO;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import br.com.joao.barber_api.entity.Client_Entity;
import br.com.joao.barber_api.repository.IClientRepository;
import br.com.joao.barber_api.service.IClientService;
import br.com.joao.barber_api.service.query.IClientQueryService;
import lombok.AllArgsConstructor;
import org.springframework.web.server.ResponseStatusException;

import java.nio.CharBuffer;

@Repository
@AllArgsConstructor
public class ClientService implements IClientService{
    private final IClientRepository repository;
    private final IClientQueryService queryService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Client_Entity save(Client_Entity entity) {
        queryService.verifyEmail(entity.getEmail());
        queryService.verifyPhone(entity.getPhone());
        return repository.save(entity);
    }
    @Override
    public Client_Entity register(Client_Entity entity) {
        queryService.verifyEmail(entity.getEmail());
        queryService.verifyPhone(entity.getPhone());
        entity.setPassword(passwordEncoder.encode(entity.getPassword()));
        return repository.save(entity);
    }
    @Override
    public Client_Entity update(Long id,Client_Entity entity) {
        queryService.verifyEmail(id,entity.getEmail());
        queryService.verifyPhone(id,entity.getPhone());

        var stored = queryService.findById(id);
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

    @Override
    public ClientLoginDTO login(CredentialsDTO credentialsDTO) {
        Client_Entity client = repository.findByEmail(credentialsDTO.login())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "User does not exist"));
        if(passwordEncoder.matches(CharBuffer.wrap(credentialsDTO.password()), client.getPassword())){
            return new ClientLoginDTO(client);
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Wrong password");

    }
}
