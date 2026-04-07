package fr.formation.ioc.factory;

import fr.formation.ioc.instrument.Guitare;
import fr.formation.ioc.musicien.Guitariste;

public class MusicienFactory {
    public static Guitariste creerGuitariste() {
        Guitariste guitariste = new Guitariste(InstrumentFactory.creerGuitare());

        // guitariste.setGuitare(new Guitare());

        return guitariste;
    }
}
