package com.desafio.gerenciamento_de_votos.dto;

import com.desafio.gerenciamento_de_votos.enums.PautaEnum;
import com.desafio.gerenciamento_de_votos.model.Voto;
import lombok.Getter;
import lombok.Setter;
import lombok.Builder;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PautaDTO {

    private String titulo;

    private String descricao;

    private LocalDateTime dtAbertura;

    private LocalDateTime dtFechamento;

    private PautaEnum status;

}
