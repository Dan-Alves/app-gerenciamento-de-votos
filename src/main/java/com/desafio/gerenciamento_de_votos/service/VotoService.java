package com.desafio.gerenciamento_de_votos.service;

import com.desafio.gerenciamento_de_votos.enums.PautaEnum;
import com.desafio.gerenciamento_de_votos.model.Pauta;
import com.desafio.gerenciamento_de_votos.model.Voto;
import com.desafio.gerenciamento_de_votos.repository.VotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class VotoService {

    @Autowired
    private VotoRepository repository;

    public Optional<Voto> findByPautaIdAndAssociadoId(String pautaId, String associadoId) {
        return repository.findByPautaIdAndAssociadoId(pautaId, associadoId);
    }

    public Voto save(Voto voto) {
        return repository.save(voto);
    }

}
