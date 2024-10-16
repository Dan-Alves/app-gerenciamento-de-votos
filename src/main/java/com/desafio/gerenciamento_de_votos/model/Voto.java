package com.desafio.gerenciamento_de_votos.model;

import jakarta.persistence.Column;
import jakarta.persistence.GenerationType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.Getter;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "votos")
public class Voto {

    @Id
    private Long id;

    private String voto;

    private Pauta pauta;

    private String associadoId;
}
