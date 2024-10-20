package com.desafio.gerenciamento_de_votos.service;

import com.desafio.gerenciamento_de_votos.enums.PautaEnum;
import com.desafio.gerenciamento_de_votos.model.Pauta;
import com.desafio.gerenciamento_de_votos.model.Resultado;
import com.desafio.gerenciamento_de_votos.model.Voto;
import com.desafio.gerenciamento_de_votos.repository.PautaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

public class PautaServiceTest {

    @InjectMocks
    private PautaService pautaService;

    @Mock
    private PautaRepository pautaRepository;

    @Mock
    private VotoService votoService;

    private Pauta pauta;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        pauta = new Pauta();
        pauta.setId("671578a25e6fa73b9857c23c");
        pauta.setTitulo("Pauta Teste");
        pauta.setStatus(PautaEnum.CLOSED);
    }

    @Test
    public void testSalvar() {
        when(pautaRepository.save(pauta)).thenReturn(pauta);

        Pauta savedPauta = pautaService.salvar(pauta);

        assertEquals(pauta, savedPauta);
        verify(pautaRepository, times(1)).save(pauta);
    }

    @Test
    public void testFindAll() {
        List<Pauta> pautas = new ArrayList<>();
        pautas.add(pauta);

        when(pautaRepository.findAll()).thenReturn(pautas);

        List<Pauta> pautasEncontradas = pautaService.findAll();

        assertEquals(1, pautasEncontradas.size());
        verify(pautaRepository, times(1)).findAll();
    }

    @Test
    public void testAbrirSessao() {
        pauta.setStatus(PautaEnum.CLOSED);
        when(pautaRepository.findById("671578a25e6fa73b9857c23c")).thenReturn(Optional.of(pauta));
        when(pautaRepository.save(any(Pauta.class))).thenReturn(pauta);

        Pauta pautaAberta = pautaService.abrirSessao("671578a25e6fa73b9857c23c", 1);

        assertEquals(PautaEnum.OPEN, pautaAberta.getStatus());
        assertNotNull(pautaAberta.getDtAbertura());
        verify(pautaRepository, times(1)).findById("671578a25e6fa73b9857c23c");
        verify(pautaRepository, times(1)).save(any(Pauta.class));
    }

    @Test
    public void testVotar() {
        String pautaId = "671578a25e6fa73b9857c23c";
        String associadoId = "123456";

        Pauta pauta = new Pauta();
        pauta.setId(pautaId);
        pauta.setStatus(PautaEnum.OPEN);
        pauta.setDtAbertura(LocalDateTime.now());
        pauta.setDuracao(1);
        pauta.setDtFechamento(LocalDateTime.now().plusMinutes(1));

        Voto voto = new Voto();
        voto.setVoto("SIM");
        voto.setPautaId("671578a25e6fa73b9857c23c");
        voto.setAssociadoId("associadoId");

        when(pautaRepository.findById("671578a25e6fa73b9857c23c")).thenReturn(Optional.of(pauta));
        when(votoService.findByPautaIdAndAssociadoId("671578a25e6fa73b9857c23c", "associadoId")).thenReturn(Optional.empty());
        when(votoService.save(voto)).thenReturn(voto);
        when(pautaRepository.save(any(Pauta.class))).thenReturn(pauta);

        String result = pautaService.votar(voto, "671578a25e6fa73b9857c23c", "123456");

        assertEquals("Voto computado com sucesso", result);
        verify(pautaRepository, times(1)).findById("671578a25e6fa73b9857c23c");
        verify(votoService, times(1)).save(voto);
    }

    @Test
    public void testObterResultado() {
        Set<Voto> votos = new HashSet<>();
        Voto votoSim = new Voto();
        votoSim.setVoto("SIM");
        Voto votoNao = new Voto();
        votoNao.setVoto("NAO");
        votos.add(votoSim);
        votos.add(votoNao);
        pauta.setVotos(votos);

        when(pautaRepository.findById("671578a25e6fa73b9857c23c")).thenReturn(Optional.of(pauta));

        Resultado resultado = pautaService.obterResultado("671578a25e6fa73b9857c23c");

        assertEquals("Pauta Teste", resultado.getTitulo());
        assertEquals(1, resultado.getSim());
        assertEquals(1, resultado.getNao());
        verify(pautaRepository, times(1)).findById("671578a25e6fa73b9857c23c");
    }
}
