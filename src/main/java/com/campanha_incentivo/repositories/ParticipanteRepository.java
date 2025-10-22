package com.campanha_incentivo.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.campanha_incentivo.entities.ParticipanteEntity;

@Repository
public interface ParticipanteRepository  extends JpaRepository<ParticipanteEntity, Long>{

    Optional<ParticipanteEntity> findByCpf(String cpf);
}