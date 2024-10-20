package com.desafio.gerenciamento_de_votos.exceptions;

import org.springframework.http.HttpStatus;

import java.util.Arrays;
import java.util.List;

public class ApiMensagensDeErro {

    private HttpStatus status;
    private List<String> errors;

    public ApiMensagensDeErro(HttpStatus status, List<String> errors) {
        super();
        this.status = status;
        this.errors = errors;
    }

    public ApiMensagensDeErro(HttpStatus status, String error) {
        super();
        this.status = status;
        errors = Arrays.asList(error);
    }
}
