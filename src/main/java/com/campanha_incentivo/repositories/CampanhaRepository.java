package com.campanha_incentivo.repositories;



import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.campanha_incentivo.model.Campanha;


@Repository
public interface CampanhaRepository  extends JpaRepository<Campanha, Long>{

    Optional<Campanha> findByNome(String campanha);

}