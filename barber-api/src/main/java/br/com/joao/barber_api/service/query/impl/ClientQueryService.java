package br.com.joao.barber_api.service.query.impl;

import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Service;

import br.com.joao.barber_api.entity.Client_Entity;
import br.com.joao.barber_api.exceptions.NotFoundException;
import br.com.joao.barber_api.exceptions.PhoneInUseException;
import br.com.joao.barber_api.repository.IClientRepository;
import br.com.joao.barber_api.service.query.IClientQueryService;
import lombok.AllArgsConstructor;


@Service
@AllArgsConstructor
public class ClientQueryService implements IClientQueryService{

    private final IClientRepository repository;

    @Override
    public Client_Entity findById(long id) {
        // TODO Auto-generated method stub
        return this.repository.findById(id).orElseThrow(() -> new NotFoundException("Não foi encontrado id: "+ id));
    }
    @Override
    public Client_Entity findByEmail(String Email) {
        // TODO Auto-generated method stub
        return this.repository.findByEmail(Email).orElseThrow(() -> new NotFoundException("Não foi encontrado email: "+ Email));
    }



    @Override
    public List<Client_Entity> list() {
        // TODO Auto-generated method stub
        return this.repository.findAll();
    }

    @Override
    public void verifyPhone(String phone) {
        if (repository.existsByPhone(phone)) {
            var message = "O telefone " + phone + " já está em uso";
            throw new PhoneInUseException(message);
        };
    }

    @Override
    public void verifyPhone(long id, String phone) {
        var optional = repository.findByPhone(phone);
        if (optional.isPresent() && !Objects.equals(optional.get().getPhone(), phone)) {
            var message = "O telefone " + phone + " já está em uso";
            throw new PhoneInUseException(message);
        }
    }

    @Override
    public void verifyEmail(String email) {
        if (repository.existsByEmail(email)) {
            var message = "O e-mail " + email + " já está em uso";
            throw new PhoneInUseException(message);
        }
    }

    @Override
    public void verifyEmail(final long id, final String email) {
        var optional = repository.findByEmail(email);
        if (optional.isPresent() && optional.get().getId() != id) {
            var message = "O e-mail " + email + " já está em uso";
            throw new PhoneInUseException(message);
        }
    }

}
