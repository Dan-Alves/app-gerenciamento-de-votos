package com.desafio.gerenciamento_de_votos.repository;

import com.desafio.gerenciamento_de_votos.model.Pauta;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PautaRepository extends MongoRepository<Pauta, String> {

}
