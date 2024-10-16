package com.desafio.gerenciamento_de_votos.repository;

import com.desafio.gerenciamento_de_votos.model.Voto;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VotoRepository extends MongoRepository<Voto, String> {

    Optional<Voto> findByPautaIdAndAssociadoId(String pautaId, String associadoId);

}
