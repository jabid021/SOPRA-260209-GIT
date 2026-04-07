package fr.formation.ioc;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import fr.formation.ioc.musicien.Guitariste;

@Component
public class ApplicationConsole implements CommandLineRunner {
    private Guitariste guitariste;

    public ApplicationConsole(Guitariste guitariste) {
        this.guitariste = guitariste;
    }

    @Override
    public void run(String... args) throws Exception {
        this.guitariste.jouer();
    }

}
