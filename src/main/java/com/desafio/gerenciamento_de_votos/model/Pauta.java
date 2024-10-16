package com.desafio.gerenciamento_de_votos.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import lombok.Data;
import lombok.Getter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Data
@Document(collection = "pautas")
public class Pauta {

    @Id
    private Long id;

    private String titulo;

    private String descricao;

    private LocalDateTime dtAbertura;

    private LocalDateTime dtFechamento;

    private Set<Voto> votos = new HashSet<>();

}
