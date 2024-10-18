package com.desafio.gerenciamento_de_votos.controller;

import com.desafio.gerenciamento_de_votos.model.Voto;
import com.desafio.gerenciamento_de_votos.service.PautaService;
import com.desafio.gerenciamento_de_votos.service.VotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/voto")
public class VotoController {

    @Autowired
    private PautaService pautaService;

    @Autowired
    private VotoService service;

    @PostMapping("/pautaId/{pautaId}/associadoId/{associadoId}")
    public ResponseEntity<String> votar(@RequestBody Voto voto,
                                        @PathVariable String pautaId,
                                        @PathVariable String associadoId) {
        return ResponseEntity.ok(pautaService.votar(voto, pautaId, associadoId));
    }

}
