package com.desafio.gerenciamento_de_votos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class GerenciamentoDeVotosApplication {

	public static void main(String[] args) {
		SpringApplication.run(GerenciamentoDeVotosApplication.class, args);
	}

}
