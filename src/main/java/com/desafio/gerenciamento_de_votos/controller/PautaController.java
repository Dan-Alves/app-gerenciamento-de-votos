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

    @GetMapping
    public ResponseEntity<List<PautaDTO>> get() {
        List<Pauta> pautas = service.findAll();
        return ResponseEntity.ok(mapper.convert(pautas));
    }

    @PostMapping
    public ResponseEntity<Pauta> criarPauta(@RequestBody Pauta pauta) {
        return ResponseEntity.ok(service.save(pauta));
    }

    @PostMapping("/{id}/abrir-sessao")
    public ResponseEntity<Pauta> abrirSessao(@PathVariable String id,
                                             @RequestParam(defaultValue = "1") int duration) {
        return ResponseEntity.ok(service.abrirSessao(id, duration));
    }

}
