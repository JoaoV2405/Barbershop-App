package br.com.joao.barber_api.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.joao.barber_api.controller.request.SaveClientRequest;
import br.com.joao.barber_api.controller.request.UpdateClientRequest;
import br.com.joao.barber_api.controller.response.ClientDetailResponse;
import br.com.joao.barber_api.controller.response.ListClientResponse;
import br.com.joao.barber_api.controller.response.SaveClientResponse;
import br.com.joao.barber_api.controller.response.UpdateClientResponse;
import br.com.joao.barber_api.entity.Client_Entity;
import br.com.joao.barber_api.mapper.IClientMapper;
import br.com.joao.barber_api.service.IClientService;
import br.com.joao.barber_api.service.query.IClientQueryService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("clients")
@AllArgsConstructor
public class ClientController {
    
    private final IClientService service;
    private final IClientQueryService queryService;
    private final IClientMapper mapper;

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    SaveClientResponse save (@RequestBody @Valid final SaveClientRequest request) {
        var entity = mapper.toEntity(request);
        service.save(entity);
        
        return mapper.toSaveResponse(entity);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void delete(@PathVariable final Long id){
        service.delete(id);
    }

    @GetMapping("{id}")
    ClientDetailResponse findById(@PathVariable final Long id) {
        var entity = queryService.findById(id);
        return mapper.toDetailResponse(entity);
    }

    @GetMapping()
    List<ListClientResponse> listClients() {
        var entities = queryService.list();
        return mapper.toListResponse(entities);
    }
    
    @PutMapping("{id}")
    UpdateClientResponse update(@PathVariable final long id, @RequestBody @Valid final UpdateClientRequest request){
        var entity = mapper.toEntity(id, request);
        service.update(entity);
        return mapper.toUpdateResponse(entity);
    }
    
    
    
}
