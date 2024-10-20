package com.desafio.gerenciamento_de_votos.controller;

import com.desafio.gerenciamento_de_votos.dto.PautaDTO;
import com.desafio.gerenciamento_de_votos.dto.ResultadoDTO;
import com.desafio.gerenciamento_de_votos.dto.VotoDTO;
import com.desafio.gerenciamento_de_votos.mapper.ResponseMapper;
import com.desafio.gerenciamento_de_votos.model.Pauta;
import com.desafio.gerenciamento_de_votos.model.Resultado;
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
    public ResponseEntity<List<PautaDTO>> obterPautas() {
        List<Pauta> pautas = service.findAll();
        return ResponseEntity.ok(mapper.convert(pautas));
    }

    @PostMapping
    public ResponseEntity<PautaDTO> criarPauta(@RequestBody Pauta pauta) {
        Pauta p = service.salvar(pauta);
        return ResponseEntity.ok(mapper.convert(p));
    }

    @PostMapping("/{pautaId}/abrir-sessao")
    public ResponseEntity<PautaDTO> abrirSessao(@PathVariable String pautaId,
                                             @RequestParam(defaultValue = "1") int duration) {
        Pauta pauta = service.abrirSessao(pautaId, duration);
        return ResponseEntity.ok(mapper.convert(pauta));
    }

    @GetMapping("/resultado/{pautaId}")
    public ResponseEntity<ResultadoDTO> obterResultado(@PathVariable String pautaId) {
        Resultado resultado = service.obterResultado(pautaId);
        return ResponseEntity.ok(mapper.convert(resultado));
    }

}
