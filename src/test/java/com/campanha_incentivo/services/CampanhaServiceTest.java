package com.campanha_incentivo.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.BDDMockito.*;
import static org.mockito.Mockito.times;

import com.campanha_incentivo.dtos.CampanhaDto;
import com.campanha_incentivo.entities.CampanhaEntity;
import com.campanha_incentivo.mapper.CampanhaMapper;
import com.campanha_incentivo.repositories.CampanhaRepository;

@ExtendWith(MockitoExtension.class)
public class CampanhaServiceTest {

    @Mock
    private CampanhaRepository campanhaRepository;

    @InjectMocks
    private CampanhaService campanhaService;

    @Mock
    private CampanhaMapper campanhaMapper;

    private CampanhaDto  campanhaDto;
    private CampanhaEntity campanha;

    private CampanhaDto  campanhaDto1;
    private CampanhaEntity campanha1;


    @BeforeEach
    public void stup(){
        LocalDateTime inicio = LocalDateTime.now();
        LocalDateTime fim = LocalDateTime.now().plusDays(10);
        campanhaDto = new CampanhaDto(null,"Vendas+",inicio, fim,"Vender mais e mais");
        campanha = new CampanhaEntity(1L, "Vendas+", inicio, fim, "Vender mais e mais", null); // O null no final assume Participantes
        campanhaDto1 = new CampanhaDto(null,"Vendas++",inicio, fim,"Vender mais e mais muito mais");
        campanha1 = new CampanhaEntity(1L, "Vendas++", inicio, fim, "ender mais e mais muito mais", null); // O null no final assume Participantes
    }

    @DisplayName("Teste Junit para determinado objeto de campanha Quando salvar campanha retornar objeto de campanha")
    @Test
    void testCriarCampanha() {
        //Given / Arrange
        given(campanhaMapper.toEntity(campanhaDto)).willReturn(campanha);

        // When / Act
        CampanhaEntity campanhaSalva = new CampanhaEntity(1L, "Vendas+", campanha.getDataInicio(), campanha.getDataFim(), "Vender mais e mais", null);
        given(campanhaRepository.save(campanha)).willReturn(campanhaSalva);

        CampanhaDto campanhaDtoSalva = new CampanhaDto(null,"Vendas+", campanhaSalva.getDataInicio(), campanhaSalva.getDataFim(), "Vender mais e mais");
        given(campanhaMapper.tDto(campanhaSalva)).willReturn(campanhaDtoSalva);
        
        var salvarCampanha = campanhaService.criarCampanha(campanhaDto);
         // Then / Assert
         assertNotNull(salvarCampanha);
         assertEquals("Vendas+",campanha.getNome());
        
    }

    @DisplayName("Teste Junit para listar todas as campanhas")
    @Test
    void testFindAllCampanhas() {
        //Given / Arrange
        List<CampanhaEntity>campanhasRepositorio=Arrays.asList(
            campanha,
            campanha1
        );
      
        given(campanhaRepository.findAll()).willReturn(campanhasRepositorio);

        given(campanhaMapper.tDto(campanha1)).willReturn(campanhaDto1);
        given(campanhaMapper.tDto(campanha)).willReturn(campanhaDto);

        // When / Act
        List<CampanhaDto> campanhas = campanhaService.findAll();
         // Then / Assert
         assertNotNull(campanhas, "A lista de campanhas não deve ser nula.");
         assertEquals(2, campanhas.size(),"A lista deve conter 2 campanhas.");

         then(campanhaRepository).should(times(1)).findAll();
         then(campanhaMapper).should(times(1)).tDto(campanha);
         then(campanhaMapper).should(times(1)).tDto(campanha1);
        
    }


}
