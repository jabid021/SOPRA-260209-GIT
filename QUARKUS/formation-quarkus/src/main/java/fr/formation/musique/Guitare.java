package fr.formation.musique;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class Guitare {
    public String son() {
        return "GLINK GLINK GLINK";
    }
}
