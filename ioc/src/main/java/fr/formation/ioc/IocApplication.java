package fr.formation.ioc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import fr.formation.ioc.factory.MusicienFactory;
import fr.formation.ioc.musicien.Guitariste;

@SpringBootApplication
public class IocApplication {
	public static void main(String[] args) {
		SpringApplication.run(IocApplication.class, args);

		// Guitariste guitariste = new Guitariste();
		// Guitariste guitariste = MusicienFactory.creerGuitariste();

		// guitariste.jouer();
	}

	@Bean
	String son() {
		return "GLINK";
	}
}
