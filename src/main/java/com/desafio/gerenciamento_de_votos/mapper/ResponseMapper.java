package com.desafio.gerenciamento_de_votos.mapper;

import com.desafio.gerenciamento_de_votos.dto.PautaDTO;
import com.desafio.gerenciamento_de_votos.dto.VotoDTO;
import com.desafio.gerenciamento_de_votos.model.Pauta;
import com.desafio.gerenciamento_de_votos.model.Voto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ResponseMapper {

    @Autowired
    private ModelMapper modelMapper;

    public PautaDTO convert(Pauta pauta) {
        return modelMapper.map(pauta, PautaDTO.class);
    }

    public VotoDTO convert(Voto voto) {
        return modelMapper.map(voto, VotoDTO.class);
    }

    public List<PautaDTO> convert(List<Pauta> pauta) {
        return pauta.stream().map(this::convert)
                .collect(Collectors.toList());

    }

}
