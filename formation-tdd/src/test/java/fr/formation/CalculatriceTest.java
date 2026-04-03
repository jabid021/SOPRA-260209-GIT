package fr.formation;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import fr.formation.exception.CantDivideByZero;

public class CalculatriceTest {
    private Calculatrice calculatrice;

    @BeforeEach
    void setup() {
        this.calculatrice = new Calculatrice();
    }

    @Test
    void shouldReturn11When5And6() {
        // given / arrange
        int a = 5;
        int b = 6;

        // when / act
        int result = this.calculatrice.addition(a, b);

        // then / assert
        Assertions.assertEquals(11, result);
    }

    @Test
    void shouldReturn15When6And9() {
        // given / arrange
        int a = 6;
        int b = 9;

        // when / act
        int result = this.calculatrice.addition(a, b);

        // then / assert
        Assertions.assertEquals(15, result);
    }

    @Test
    void shouldReturn10When2And8() {
        // given / arrange
        int a = 2;
        int b = 8;

        // when / act
        int result = this.calculatrice.addition(a, b);

        // then / assert
        Assertions.assertEquals(10, result);
    }


    @ParameterizedTest
    @CsvSource({
        "5,6,11",
        "6,9,15",
        "7,9,16",
        "2,8,10",
        "-54,20,-34"
    })
    // @CsvFileSource("tonfichier.csv")
    void shouldAdditionOk(int a, int b, int expected) {
        // given

        // when
        int result = this.calculatrice.addition(a, b);

        // then
        Assertions.assertEquals(expected, result);
    }

    @Test
    void shouldThrowArithmeticExceptionWhenDividedBy0() {
        // given
        int a = 5;
        int b = 0;

        // when
        Assertions.assertThrows(
            CantDivideByZero.class,
            () -> this.calculatrice.division(a, b)
        );
    }
}
