package fr.formation.musique;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped // Permet de dire à Quarkus de gérer l'instance
public class Guitariste {
    @Inject // Permet de demander à Quarkus l'instance qu'il gère : injection de dépendance
    private Guitare guitare;

    public void jouer() {
        System.out.println("Le guitariste joue " + this.guitare.son());
    }
}
