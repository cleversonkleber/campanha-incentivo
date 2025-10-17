package com.campanha_incentivo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.campanha_incentivo.entities.Evento;


@Repository
public interface EventoRepository  extends JpaRepository<Evento, Long>{

   

}