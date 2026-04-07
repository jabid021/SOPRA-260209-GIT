package fr.formation.ioc.factory;

import fr.formation.ioc.instrument.Guitare;

public class InstrumentFactory {
    public static Guitare creerGuitare() {
        return new Guitare("GLINK");
    }
}
