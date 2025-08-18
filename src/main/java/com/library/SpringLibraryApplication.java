package com.library;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringLibraryApplication {

	public static void main(String[] args) {
		// carrega o .env da raiz do projeto
		Dotenv dotenv = Dotenv.load();

		// injeta cada variável do .env como System property
		dotenv.entries().forEach(entry ->
				System.setProperty(entry.getKey(), entry.getValue())
		);

		SpringApplication.run(SpringLibraryApplication.class, args);
	}

}
