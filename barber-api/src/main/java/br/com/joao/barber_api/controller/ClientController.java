package br.com.joao.barber_api.controller;

import br.com.joao.barber_api.controller.dto.ClientLoginDTO;
import br.com.joao.barber_api.controller.dto.ClientRegisterDTO;

import br.com.joao.barber_api.controller.dto.ScheduleDetailDTO;
import br.com.joao.barber_api.entity.Client_Entity;
import br.com.joao.barber_api.entity.Schedule_Entity;
import br.com.joao.barber_api.mapper.ClientMapper;
import br.com.joao.barber_api.mapper.IScheduleMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.joao.barber_api.controller.dto.ClientDTO;
import br.com.joao.barber_api.service.IClientService;
import br.com.joao.barber_api.service.query.IClientQueryService;
import br.com.joao.barber_api.service.query.IScheduleQueryService;
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
import org.springframework.web.bind.annotation.ResponseBody;


@RestController
@RequestMapping("clients")
@AllArgsConstructor
public class ClientController {
    
    private final IClientService service;
    private final IClientQueryService queryService;
    private final IScheduleQueryService scheduleQueryService;
    private final ClientMapper clientMapper;
    @Qualifier("IScheduleMapper")
    private final IScheduleMapper scheduleMapper;

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    ClientDTO save (@RequestBody @Valid final ClientRegisterDTO request) {
        var entity = clientMapper.ClientRegisterDTOtoEntity(request);
        service.save(entity);

        return clientMapper.Client_EntityToClientDTO(entity);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void delete(@PathVariable final Long id){
        service.delete(id);
    }

    @GetMapping("{id}")
    ClientDTO findById(@PathVariable("id") final Long id) {
        var entity = queryService.findById(id);
        return clientMapper.Client_EntityToClientDTO(entity);
    }

    @GetMapping("/schedules")
    public ResponseEntity<?> getMySchedules() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Usuário não autenticado");
        }
        Object principal = authentication.getPrincipal();

        if (principal instanceof ClientLoginDTO userDetails) {
            String userEmail = userDetails.getEmail();
            Client_Entity client = queryService.findByEmail(userEmail);
            List<Schedule_Entity> schedules = scheduleQueryService.findByClientId(client.getId());
            List<ScheduleDetailDTO> scheduleDto = scheduleMapper.Schedule_EntityToScheduleDetailDTOList(schedules);
            return ResponseEntity.ok(scheduleDto);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Usuário inválido");
        }
    }

    @GetMapping()
    List<ClientDTO> listClients() {
        var entities = queryService.list();
        return clientMapper.Client_EntityToClientDTOList(entities);
    }
    
    @PutMapping("{id}")
    ClientDTO update(@PathVariable final long id, @RequestBody @Valid final ClientDTO request){
        var entity = clientMapper.toEntity(request);
        service.update(id,entity);
        return clientMapper.Client_EntityToClientDTO(entity);
    }
    
    
    
}
