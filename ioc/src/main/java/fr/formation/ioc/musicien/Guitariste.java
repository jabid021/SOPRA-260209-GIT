package fr.formation.ioc.musicien;

import org.springframework.stereotype.Component;

import fr.formation.ioc.instrument.Guitare;

@Component
public class Guitariste {
    // private Guitare guitare = new Guitare();
    private Guitare guitare;

    public Guitare getGuitare() {
        return guitare;
    }

    public Guitariste(Guitare guitare) {
        this.guitare = guitare;
    }

    public void setGuitare(Guitare guitare) {
        this.guitare = guitare;
    }

    public void jouer() {
        System.out.println("Le guitariste joue " + guitare);
    }
}
