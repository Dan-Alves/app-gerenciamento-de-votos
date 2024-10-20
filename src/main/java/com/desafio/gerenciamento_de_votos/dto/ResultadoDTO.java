package com.desafio.gerenciamento_de_votos.dto;

import com.desafio.gerenciamento_de_votos.model.Voto;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResultadoDTO {

    private String titulo;

    private Long sim;

    private Long nao;

}
