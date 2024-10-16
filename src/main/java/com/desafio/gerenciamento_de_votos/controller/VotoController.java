package com.desafio.gerenciamento_de_votos.controller;

import com.desafio.gerenciamento_de_votos.model.Voto;
import com.desafio.gerenciamento_de_votos.service.VotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/voto")
public class VotoController {

    @Autowired
    private VotoService service;

//    @PostMapping()
//    public ResponseEntity<String> save(@RequestBody Voto voto, @RequestParam Long pautaId) {
//    }

}
