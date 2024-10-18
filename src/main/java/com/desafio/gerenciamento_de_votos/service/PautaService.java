package com.desafio.gerenciamento_de_votos.service;

import com.desafio.gerenciamento_de_votos.enums.PautaEnum;
import com.desafio.gerenciamento_de_votos.model.Pauta;
import com.desafio.gerenciamento_de_votos.model.Voto;
import com.desafio.gerenciamento_de_votos.repository.PautaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

@Service
public class PautaService {

    @Autowired
    private PautaRepository repository;

    @Autowired
    private VotoService votoService;

    public Pauta save(Pauta pauta) {
        if (pauta.getStatus() == null) {
            pauta.setStatus(PautaEnum.CLOSED);
        }
        return repository.save(pauta);
    }

    public List<Pauta> findAll() {
        return repository.findAll();
    }

    public Pauta abrirSessao(String pautaId, int duracaoMinutos) {
        Optional<Pauta> pautaOpt = repository.findById(pautaId);

        if (pautaOpt.isPresent()) {
            Pauta p = pautaOpt.get();
            p.setDtAbertura(LocalDateTime.now());
            p.setDtFechamento(LocalDateTime.now().plusMinutes(duracaoMinutos));
            p.setStatus(PautaEnum.OPEN);
        }

        throw new RuntimeException("Pauta não encontrada.");

    }

    public String votar(Voto voto, String pautaId, String associadoId) {
        verificaStatus(pautaId);

        Optional<Pauta> pautaOpt = repository.findById(pautaId);

        if(pautaOpt.isPresent()) {
            Pauta p = pautaOpt.get();

            if (!p.getStatus().equals(PautaEnum.OPEN)) {
                throw new RuntimeException("A sessão não está aberta.");
            }

        }

        Optional<Voto> votoExistente = votoService.findByPautaIdAndAssociadoId(pautaId, associadoId);
        if (votoExistente.isPresent()) {
            throw new RuntimeException("Associado já votou na pauta.");
        }

        votoService.save(voto);

        return "Voto computado com sucesso";

    }

    private void verificaStatus(String pautaId) {
        Optional<Pauta> pautaOpt = repository.findById(pautaId);

        if(pautaOpt.isPresent()) {
            Pauta p = pautaOpt.get();

            if (p.getStatus().equals(PautaEnum.OPEN)) {
                long tempoDecorrido = ChronoUnit.MINUTES.between(p.getDtAbertura(), LocalDateTime.now());

                if (tempoDecorrido >= p.getDuracao()) {
                    p.setStatus(PautaEnum.CLOSED);
                    repository.save(p);
                }
            }
        }
    }

//    @Scheduled(fixedRate = 60000)
//    public void fecharSessoesExpiradas() {
//        List<Pauta> pautasAbertas = repository.findAll().stream()
//                .filter(p -> p.getStatus() == PautaEnum.OPEN)
//                .toList();
//
//        for (Pauta pauta : pautasAbertas) {
//            LocalDateTime now = LocalDateTime.now();
//            long tempoDecorrido = ChronoUnit.MINUTES.between(pauta.getDtAbertura(), now);
//
//            if (tempoDecorrido >= pauta.getDuracao()) {
//                pauta.setStatus(PautaEnum.CLOSED);
//                repository.save(pauta);
//                System.out.println("Sessão da pauta " + pauta.getTitulo() + " foi fechada automaticamente.");
//            }
//        }
//    }

}
