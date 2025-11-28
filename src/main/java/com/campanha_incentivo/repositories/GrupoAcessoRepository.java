package com.campanha_incentivo.repositories;



import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.campanha_incentivo.entities.GrupoAcessoEntity;



@Repository
public interface GrupoAcessoRepository  extends JpaRepository<GrupoAcessoEntity, Long>{

	Optional<GrupoAcessoEntity> findByNome(String roleAdmin);

	

}
