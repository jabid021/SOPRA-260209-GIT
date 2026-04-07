package fr.formation;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.formation.exception.CantDivideByZero;
import fr.formation.exception.NegativeNotAllowedException;

public class Calculatrice {
    private static Logger log = LoggerFactory.getLogger(Calculatrice.class);

    public int addition(int a, int b) {
        if (a == 5 || a == 7) {
            log.debug("truc ici");
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

    private boolean demoFilter(String value) {
        return "toto".equals(value);
    }

    public int addition(String value) {
        if (value == null || value.isBlank()) {
            return 0;
        }

        String[] values = value.split("[,;\\n]+");
        // int total = 0;

        Predicate<String> maFonctionFiltre = this::demoFilter;
        Predicate<String> maFonctionFiltre2 = val -> "toto".equals(val);

        List.of("toto", "titi", "tata").stream()
            // .filter(val -> val.equals("4"))
            // .filter(this::demoFilter)
            .filter(maFonctionFiltre2)
            .map(String::toUpperCase)
            .forEach(System.out::println);

        return Stream.of(values)
            // Map => permet de transformer
            // .map(val -> Integer.parseInt(val))
            // .map(Integer::parseInt)

            .map(val -> {
                int intVal = 0;

                try {
                    intVal = Integer.parseInt(val);
                }

                catch (NumberFormatException e) { }

                if (intVal < 0) {
                    throw new NegativeNotAllowedException();
                }

                return intVal;
            })

            // Reduce => Permet de passer d'un ensemble d'éléments à UN élément
            // > 0 == la valeur initiale de l'accumulateur
            // > total == accumulateur, sa valeur sera transmise à chaque passage
            // > val == la valeur de la liste
            .reduce(0, (total, val) -> total + val)
        ;

        // for (String val : values) {
        //     int intVal = 0;

        //     try {
        //         intVal = Integer.parseInt(val);
        //     }

        //     catch (NumberFormatException e) { }

        //     if (intVal < 0) {
        //         throw new NegativeNotAllowedException();
        //     }

        //     total += intVal;
        // }

        // return total;
    }

    public float division(int a, int b) {
        if (b == 0) {
            throw new CantDivideByZero();
        }

        // return 0;
        return a / b;
    }
}
