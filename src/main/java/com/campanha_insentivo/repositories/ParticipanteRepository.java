package com.campanha_insentivo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.campanha_insentivo.model.Participante;

@Repository
public interface ParticipanteRepository  extends JpaRepository<Participante, Long>{

}