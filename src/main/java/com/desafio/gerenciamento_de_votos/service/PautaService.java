package com.desafio.gerenciamento_de_votos.service;

import com.desafio.gerenciamento_de_votos.dto.ResultadoDTO;
import com.desafio.gerenciamento_de_votos.enums.PautaEnum;
import com.desafio.gerenciamento_de_votos.model.Pauta;
import com.desafio.gerenciamento_de_votos.model.Resultado;
import com.desafio.gerenciamento_de_votos.model.Voto;
import com.desafio.gerenciamento_de_votos.repository.PautaRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PautaService {

    @Autowired
    private PautaRepository repository;

    @Autowired
    private VotoService votoService;

    private static final Logger logger = LogManager.getLogger(PautaService.class);

    public Pauta salvar(Pauta pauta) {
        logger.info("Início do método salvar para a pauta: {}", pauta);

        if (pauta.getStatus() == null) {
            pauta.setStatus(PautaEnum.CLOSED);
        }

        Pauta pautaSalva = repository.save(pauta);

        logger.info("Fim do método save() para a pauta: {}", pautaSalva);
        return pautaSalva;
    }

    public List<Pauta> findAll() {
        logger.info("Início do método findAll()");

        List<Pauta> pautas = repository.findAll();

        logger.info("Fim do método findAll() - Total de pautas: {}", pautas.size());
        return pautas;
    }

    public Pauta abrirSessao(String pautaId, Integer duracaoMinutos) {
        logger.info("Início do método abrirSessao() para a pautaId: {}", pautaId);

        Pauta pauta = repository.findById(String.valueOf(new ObjectId(pautaId)))
                .orElseThrow(() -> new RuntimeException("Pauta não encontrada para o ID: " + pautaId));

        pauta.setDtAbertura(LocalDateTime.now());
        pauta.setDuracao(duracaoMinutos == null ? 1 : duracaoMinutos);
        pauta.setDtFechamento(LocalDateTime.now().plusMinutes(duracaoMinutos));
        pauta.setStatus(PautaEnum.OPEN);
        repository.save(pauta);

        logger.info("Fim do método abrirSessao() para a pautaId: {}", pautaId);
        return pauta;
    }

    public String votar(Voto voto, String pautaId, String associadoId) {
        logger.info("Início do método votar() para a pautaId: {} e associadoId: {}", pautaId, associadoId);

        verificaStatus(pautaId);
        Pauta pauta = repository.findById(String.valueOf(new ObjectId(pautaId)))
                .orElseThrow(() -> new RuntimeException("Pauta não encontrada para o ID: " + pautaId));

        if (!pauta.getStatus().equals(PautaEnum.OPEN)) {
            throw new RuntimeException("A sessão não está aberta.");
        }


        Optional<Voto> votoExistente = votoService.findByPautaIdAndAssociadoId(pautaId, associadoId);
        if (votoExistente.isPresent()) {
            throw new RuntimeException("Associado já votou na pauta.");
        }

        voto.setPautaId(pautaId);
        voto.setAssociadoId(associadoId);
        pauta.getVotos().add(voto);
        votoService.save(voto);
        repository.save(pauta);

        logger.info("Fim do método votar() - Voto computado com sucesso para a pautaId: {}", pautaId);
        return "Voto computado com sucesso";

    }

    public Resultado obterResultado(String pautaId) {
        logger.info("Início do método obterResultado() para a pautaId: {}", pautaId);

        Pauta pauta = repository.findById(String.valueOf(new ObjectId(pautaId)))
                .orElseThrow(() -> new RuntimeException("Pauta não encontrada para o ID: " + pautaId));

        long votosSim = pauta.getVotos().stream()
                .filter(voto -> voto.getVoto().equals("SIM"))
                .count();

        long votosNao = pauta.getVotos().stream()
                .filter(voto -> voto.getVoto().equals("NAO"))
                .count();

        Resultado resultado = Resultado.builder()
                .titulo(pauta.getTitulo())
                .sim(votosSim)
                .nao(votosNao)
                .build();

        logger.info("Fim do método obterResultado() - Resultado: {}", resultado);
        return resultado;
    }

    private void verificaStatus(String pautaId) {
        logger.info("Início do método verificaStatus() para a pautaId: {}", pautaId);

        Pauta pauta = repository.findById(String.valueOf(new ObjectId(pautaId)))
                .orElseThrow(() -> new RuntimeException("Pauta não encontrada para o ID: " + pautaId));

        if (pauta.getStatus().equals(PautaEnum.OPEN)) {
            long tempoDecorrido = ChronoUnit.MINUTES.between(pauta.getDtAbertura(), LocalDateTime.now());

            if (tempoDecorrido >= pauta.getDuracao()) {
                pauta.setStatus(PautaEnum.CLOSED);
                repository.save(pauta);
            }
        }

        logger.info("Fim do método verificaStatus() para a pautaId: {}", pautaId);
    }

}
