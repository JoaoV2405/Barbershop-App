package br.com.joao.barber_api.controller;

import br.com.joao.barber_api.config.UserAuthProvider;
import br.com.joao.barber_api.controller.dto.ClientLoginDTO;
import br.com.joao.barber_api.controller.dto.ClientRegisterDTO;
import br.com.joao.barber_api.controller.dto.CredentialsDTO;
import br.com.joao.barber_api.entity.Client_Entity;
import br.com.joao.barber_api.mapper.ClientMapper;
import br.com.joao.barber_api.service.IClientService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;


@AllArgsConstructor
@RestController
public class AuthController {
    private final IClientService service;
    private final ClientMapper clientMapper;
    private final UserAuthProvider userAuthProvider;

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ClientLoginDTO> login (@RequestBody CredentialsDTO credentials) {
        ClientLoginDTO entity = service.login(credentials);
        entity.setToken(userAuthProvider.createToken(entity));
        return ResponseEntity.ok(entity);
    }
    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ClientLoginDTO> save (@RequestBody @Valid final ClientRegisterDTO request) {
        Client_Entity client = clientMapper.ClientRegisterDTOtoEntity(request);
        Client_Entity createdClient = service.register(client);
        ClientLoginDTO login = clientMapper.ClientRegisterDTOToClientLoginDTO(request);
        login.setToken(userAuthProvider.createToken(login));
        return ResponseEntity.created(URI.create("/clients/" + createdClient.getId())).body(login);


    }
}
