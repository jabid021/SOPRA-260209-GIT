package fr.formation;

import fr.formation.exception.CantDivideByZero;
import fr.formation.exception.NegativeNotAllowedException;

public class Calculatrice {
    public int addition(int a, int b) {
        if (a == 5 || a == 7) {
            System.out.println("truc ici");
        }

        return a + b;
    }

    public int addition(String a, String b) {
        try {
            Integer intA = Integer.parseInt(a);
            Integer intB = Integer.parseInt(b);

            return intA + intB;
        }

        catch (NumberFormatException e) {
            return 0;
        }


        // if ("14".equals(a) && "8".equals(b)) {
        //     return 22;
        // }

        // if ("12".equals(a) && "24".equals(b)) {
        //     return 36;
        // }

        // return 12;
    }

    public int addition(String value) {
        if (value == null || value.isBlank()) {
            return 0;
        }

        String[] values = value.split("[,;\\n]+");
        int total = 0;

        for (String val : values) {
            int intVal = 0;

            try {
                intVal = Integer.parseInt(val);
            }

            catch (NumberFormatException e) { }

            if (intVal < 0) {
                throw new NegativeNotAllowedException();
            }

            total += intVal;
        }

        return total;
    }

    public float division(int a, int b) {
        if (b == 0) {
            throw new CantDivideByZero();
        }

        // return 0;
        return a / b;
    }
}
