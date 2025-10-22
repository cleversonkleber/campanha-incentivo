package com.campanha_incentivo.services;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import com.campanha_incentivo.dtos.ParticipanteDTO;
import com.campanha_incentivo.entities.EventoEntity;
import com.campanha_incentivo.entities.ParticipanteEntity;
import com.campanha_incentivo.mapper.ParticipanteMapper;
import com.campanha_incentivo.repositories.ParticipanteRepository;

@ExtendWith(MockitoExtension.class)
public class ParticipanteServiceTest {

    @Mock
    private ParticipanteRepository participanteRepository;

    @InjectMocks
    private ParticipanteService participanteService;

    @Mock
    private ParticipanteMapper participanteMapper;

    private ParticipanteDTO participanteDTO;
    private ParticipanteEntity participante;

    private ParticipanteDTO  participanteDTO1;
    private ParticipanteEntity participante1;


    @BeforeEach
    public void stup(){
        participante = new ParticipanteEntity(null, "João Carlos","11111111110","joaoCarlos@j.com",null); // O null no final assume Participantes
        participanteDTO = new ParticipanteDTO(null,"João Carlos","11111111110","joaoCarlos@j.com");
        
        participante1 = new ParticipanteEntity(null, "João Jose","11111111112","joaojose@j.com", null); // O null no final assume Participantes
        participanteDTO1 = new ParticipanteDTO(null,"João Jose","11111111112","joaojose@j.com");


    }


    @Test
    void testDeveCriarParticipanteComSucessoQuandoNaoExiste() {
        //Given / Arrange
        given(participanteRepository.findByCpf(anyString())).willReturn(Optional.empty());

        given(participanteMapper.toEntity(participanteDTO)).willReturn(participante);

        ParticipanteEntity participanteSalva = new ParticipanteEntity(1L, participante.getNome_completo(),participante.getCpf(), participante.getEmail(), null);
        given(participanteRepository.save(participante)).willReturn(participanteSalva);

        ParticipanteDTO participanteDTOSalva = new ParticipanteDTO(null,participanteDTO.nome_completo(), participanteDTO.cpf(), participanteDTO.email());
        given(participanteMapper.tDto(participanteSalva)).willReturn(participanteDTOSalva);


        // When / Act
        ParticipanteDTO resultadoDTO = participanteService.criarParticipante(participanteDTO);


        assertNotNull(resultadoDTO, "Participante não pode ser nulo!");
        assertEquals(participanteDTO.cpf(),resultadoDTO.cpf(), "O CPF deve ser igual.");
        assertEquals(participanteDTO.nome_completo(),resultadoDTO.nome_completo(), "O Nomde deve ser igual.");
        assertEquals(participanteDTO.email(),resultadoDTO.email(),"Emails devem ser iguais");
        
        
        
        verify(participanteRepository, times(1)).findByCpf(participanteDTO.cpf());
        verify(participanteMapper, times(1)).toEntity(resultadoDTO);
        verify(participanteRepository, times(1)).save(participante);
        verify(participanteMapper, times(1)).tDto(participanteSalva);
        


    }

    @Test
    void testFindAllParticipanteList_retorneParticipantes() {
        // Given / Arrange
        given(participanteRepository.findAll()).willReturn(Collections.emptyList());
        List<ParticipanteDTO> participantesDtos = participanteService.findAll();
        // Quando / Act
        // Então / Assert
        assertNotNull(participantesDtos, "A lista de DTOs não deve ser nula, mesmo que vazia.");
        assertTrue(participantesDtos.isEmpty(),"A lista de DTOs deve estar vazia.");
        assertEquals(0, participantesDtos.size(), "O tamanho da lista deve ser 0.");

        verify(participanteRepository, times(1)).findAll();
        verify(participanteMapper, never()).tDto(any(ParticipanteEntity.class));
    }
    @Test
    void testFindAll_retornaListaDeParticipantesQuandoExistemRegistros() {
        // Given / Arrange
        List<ParticipanteEntity> entidadesRetornadasPeloRepo = Arrays.asList(participante, participante1);


        given(participanteRepository.findAll()).willReturn(entidadesRetornadasPeloRepo);


        given(participanteMapper.tDto(participante)).willReturn(participanteDTO);
        given(participanteMapper.tDto(participante1)).willReturn(participanteDTO1);

        // Quando / Act
        List<ParticipanteDTO> participantesDtos = participanteService.findAll();

        // Então / Assert
        assertNotNull(participantesDtos, "A lista de DTOs não deve ser nula.");
        assertFalse(participantesDtos.isEmpty(), "A lista de DTOs não deve estar vazia.");
        assertEquals(2, participantesDtos.size(), "O tamanho da lista deve ser 2.");

        assertEquals(participanteDTO.cpf(), participantesDtos.get(0).cpf());
        assertEquals(participanteDTO1.cpf(), participantesDtos.get(1).cpf());

        assertEquals(participanteDTO.nome_completo(), participantesDtos.get(0).nome_completo());
        assertEquals(participanteDTO1.nome_completo(), participantesDtos.get(1).nome_completo());


        verify(participanteRepository, times(1)).findAll();

        verify(participanteMapper, times(1)).tDto(participante); 
        verify(participanteMapper, times(1)).tDto(participante1); 

    }
}
