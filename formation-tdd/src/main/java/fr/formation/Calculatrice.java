package fr.formation;

import fr.formation.exception.CantDivideByZero;

public class Calculatrice {
    public int addition(int a, int b) {
        if (a == 5 || a == 7) {
            System.out.println("truc ici");
        }

        return a + b;
    }

    public float division(int a, int b) {
        if (b == 0) {
            throw new CantDivideByZero();
        }

        // return 0;
        return a / b;
    }
}
