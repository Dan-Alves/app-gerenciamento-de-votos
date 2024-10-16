package com.desafio.gerenciamento_de_votos.dto;

import com.desafio.gerenciamento_de_votos.model.Pauta;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VotoDTO {

    private String voto;

    private Pauta pauta;

    private String associadoId;
}
