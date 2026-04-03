package fr.formation;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import fr.formation.exception.CantDivideByZero;
import fr.formation.exception.NegativeNotAllowedException;

// Charge TOUT le contexte de Spring Boot
// @SpringBootTest

// Charge UNIQUEMENT la partie Web + Securité
// @WebMvcTest

// Charge UNIQUEMENT la partie Data-JPA
// @DataJpaTest

// Exécuter le test dans un context Mockito pour simuler les interactions avec plusieurs instances
// @ExtendWith(MockitoExtension.class)
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

    @Test
    void shouldReturn12When4And8() {
        // given
        String a = "4";
        String b = "8";

        // when
        int result = this.calculatrice.addition(a, b);

        // then
        Assertions.assertEquals(12, result);
    }

    @Test
    void shouldReturn22When14And8() {
        // given
        String a = "14";
        String b = "8";

        // when
        int result = this.calculatrice.addition(a, b);

        // then
        Assertions.assertEquals(22, result);
    }

    @Test
    void shouldReturn36When12And24() {
        // given
        String a = "12";
        String b = "24";

        // when
        int result = this.calculatrice.addition(a, b);

        // then
        Assertions.assertEquals(36, result);
    }

    @ParameterizedTest
    @CsvSource({
        "5,6,11",
        "6,9,15",
        "7,9,16",
        "2,8,10",
        "-54,20,-34",
        "a,b,0",
        "5,b,0",
        "a,5,0",
        ",,0"
    })
    void shouldAdditionStringOk(String a, String b, int expected) {
        // given

        // when
        int result = this.calculatrice.addition(a, b);

        // then
        Assertions.assertEquals(expected, result);
    }

    @Test
    void shouldReturn0WhenNull() {
        // given
        String value = null;

        // when
        int result = this.calculatrice.addition(value);

        // then
        Assertions.assertEquals(0, result);
    }

    @Test
    void shouldReturn0WhenEmpty() {
        // given
        String value = "";

        // when
        int result = this.calculatrice.addition(value);

        // then
        Assertions.assertEquals(0, result);
    }

    @Test
    void shouldReturn2When2() {
        // given
        String value = "2";

        // when
        int result = this.calculatrice.addition(value);

        // then
        Assertions.assertEquals(2, result);
    }

    @Test
    void shouldReturn5When5() {
        // given
        String value = "5";

        // when
        int result = this.calculatrice.addition(value);

        // then
        Assertions.assertEquals(5, result);
    }

    @Test
    void shouldReturn36When12And5And19() {
        // given
        String value = "12,5,19";

        // when
        int result = this.calculatrice.addition(value);

        // then
        Assertions.assertEquals(36, result);
    }

    @Test
    void shouldReturn31When12And19() {
        // given
        String value = "12,19";

        // when
        int result = this.calculatrice.addition(value);

        // then
        Assertions.assertEquals(31, result);
    }

    @Test
    void shouldThrowNegativeExceptionWhenNegative() {
        // given
        String value = "12,-5,19";

        // when & then
        Assertions.assertThrows(
            NegativeNotAllowedException.class,
            () -> this.calculatrice.addition(value)
        );
    }

    @Test
    void shouldReturn31When12And19WithSemiColon() {
        // given
        String value = "12;19";

        // when
        int result = this.calculatrice.addition(value);

        // then
        Assertions.assertEquals(31, result);
    }

    @Test
    void shouldReturn31When12And19WithNewLine() {
        // given
        String value = """
                12
                19""";

        // when
        int result = this.calculatrice.addition(value);

        // then
        Assertions.assertEquals(31, result);
    }

    @Test
    void shouldReturn36When12And5And19WithMixin() {
        // given
        String value = """
                12,5
                19""";

        // when
        int result = this.calculatrice.addition(value);

        // then
        Assertions.assertEquals(36, result);
    }

    @Test
    void shouldReturn46When12And5And19And10WithMixin() {
        // given
        String value = """
                12,5
                19;10""";

        // when
        int result = this.calculatrice.addition(value);

        // then
        Assertions.assertEquals(46, result);
    }

    @Test
    void shouldReturn15WhenAAnd5And10() {
        // given
        String value = "A,5,10";

        // when
        int result = this.calculatrice.addition(value);

        // then
        Assertions.assertEquals(15, result);
    }
}
