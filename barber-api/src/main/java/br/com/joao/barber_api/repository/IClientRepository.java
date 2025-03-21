package br.com.joao.barber_api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.joao.barber_api.entity.Client_Entity;

@Repository
public interface IClientRepository extends JpaRepository<Client_Entity, Long>{
    
    boolean existsByEmail(final String email);

    boolean existsByPhone(final String phone);

    Optional<Client_Entity> findByEmail(final String email);

    Optional<Client_Entity> findByPhone(final String phone);

}
