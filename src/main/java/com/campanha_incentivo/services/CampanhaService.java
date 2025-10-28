package com.campanha_incentivo.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import java.util.logging.Logger;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import com.campanha_incentivo.dtos.CampanhaDto;
import com.campanha_incentivo.entities.CampanhaEntity;
import com.campanha_incentivo.exception.handler.ResourceNotFoundException;
import com.campanha_incentivo.mapper.CampanhaMapper;
import com.campanha_incentivo.repositories.CampanhaRepository;

@Service
public class CampanhaService {

    private Logger logger = Logger.getLogger(CampanhaService.class.getName());

    private CampanhaRepository repository;

    private CampanhaMapper mapper;
    

    public CampanhaService(CampanhaRepository repository, CampanhaMapper mapper) {
        this.repository = repository; 
        this.mapper = mapper;         
    }

    public CampanhaEntity findByName(String nome){
        Optional<CampanhaEntity> optional = repository.findByNome(nome);
        if (!optional.isPresent()){
            logger.info("Erro ao atualizar campanha!");
        }
        CampanhaEntity campanha = optional
                        .orElseThrow(() -> new ResourceNotFoundException("Não camapanha com o nome: "+ nome));
        return campanha;
    }
    @PreAuthorize("hasRole('ADMIN')")
    public CampanhaDto criarCampanha(CampanhaDto dto) {
        logger.info("Criando campanha!");
        String nomeCampanha = dto.nome().toUpperCase().toString();
        CampanhaEntity campanhaSava =mapper.toEntity(dto);
        campanhaSava.setNome(nomeCampanha);
        return mapper.tDto(repository.save(campanhaSava));
    }
    
    public List<CampanhaDto> findAll() {
        logger.info("Listar todas as campanhas!");
        return repository.findAll()
                .stream()
                .map(mapper::tDto)
                .collect(Collectors.toList());
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'GESTOR')")
    public void delete(Long id) {
        logger.info("Deletar campanha!");
        var campanha = repository.findById(id)
                            .orElseThrow(()-> new ResourceNotFoundException("Id não encontrado"));
        repository.delete(campanha);
    }

    public CampanhaDto update(CampanhaDto dto) {
        logger.info("Atualizar campanha!");
        String nomeCampanha = dto.nome().toUpperCase().toString();
        Optional<CampanhaEntity> optional = repository.findById(dto.id());
        if (!optional.isPresent()) {
            logger.info("Erro ao atualizar campanha!");
        }
        CampanhaEntity campanha = optional
                        .orElseThrow(() -> new ResourceNotFoundException("Não camapanha com o nome: "+ dto.nome()));
        campanha.setNome(nomeCampanha);
        campanha.setDataInicio(dto.dataInicio());
        campanha.setDataFim(dto.dataFim());
        repository.save(campanha);
        return mapper.tDto(campanha);

    }


}
