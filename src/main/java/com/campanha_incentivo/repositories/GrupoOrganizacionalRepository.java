package com.campanha_incentivo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.campanha_incentivo.entities.GrupoOrganizacionalEntity;



@Repository
public interface GrupoOrganizacionalRepository  extends JpaRepository<GrupoOrganizacionalEntity, Long>{



}
