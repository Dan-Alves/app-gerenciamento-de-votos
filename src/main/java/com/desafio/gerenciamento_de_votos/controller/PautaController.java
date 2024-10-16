package com.desafio.gerenciamento_de_votos.controller;

import com.desafio.gerenciamento_de_votos.dto.PautaDTO;
import com.desafio.gerenciamento_de_votos.dto.VotoDTO;
import com.desafio.gerenciamento_de_votos.mapper.ResponseMapper;
import com.desafio.gerenciamento_de_votos.model.Pauta;
import com.desafio.gerenciamento_de_votos.model.Voto;
import com.desafio.gerenciamento_de_votos.service.PautaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pauta")
public class PautaController {

    @Autowired
    private PautaService service;

    @Autowired
    private ResponseMapper mapper;


}
