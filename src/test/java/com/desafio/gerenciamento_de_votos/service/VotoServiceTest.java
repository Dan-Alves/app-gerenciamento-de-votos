package com.desafio.gerenciamento_de_votos.service;

import com.desafio.gerenciamento_de_votos.model.Voto;
import com.desafio.gerenciamento_de_votos.repository.VotoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class VotoServiceTest {

    @InjectMocks
    private VotoService votoService;

    @Mock
    private VotoRepository votoRepository;

    private Voto voto;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        voto = new Voto();
        voto.setPautaId("pautaId");
        voto.setAssociadoId("associadoId");
    }

    @Test
    public void testFindByPautaIdAndAssociadoId() {
        when(votoRepository.findByPautaIdAndAssociadoId("pautaId", "associadoId"))
                .thenReturn(Optional.of(voto));

        Optional<Voto> foundVoto = votoService.findByPautaIdAndAssociadoId("pautaId", "associadoId");

        assertTrue(foundVoto.isPresent());
        assertEquals(voto, foundVoto.get());
        verify(votoRepository, times(1)).findByPautaIdAndAssociadoId("pautaId", "associadoId");
    }

    @Test
    public void testSave() {
        when(votoRepository.save(voto)).thenReturn(voto);

        Voto savedVoto = votoService.save(voto);

        assertEquals(voto, savedVoto);
        verify(votoRepository, times(1)).save(voto);
    }
}
