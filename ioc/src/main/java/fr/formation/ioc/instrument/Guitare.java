package fr.formation.ioc.instrument;

import org.springframework.stereotype.Component;

@Component
public class Guitare {
    private String son;

    public Guitare(String son) {
        this.son = son;
    }

    @Override
    public String toString() {
        return son + " " + son + " " + son;
    }
}
