package fr.bibliotek.api;

import org.hamcrest.CoreMatchers;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mockito;

import fr.bibliotek.TestUtil;
import fr.bibliotek.api.request.CreateOrUpdateAuteurRequest;
import fr.bibliotek.enumerator.NationaliteEnum;
import fr.bibliotek.service.AuteurService;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.InjectSpy;
import io.quarkus.test.security.TestSecurity;
import io.restassured.RestAssured;
import jakarta.ws.rs.core.Response.Status;

@QuarkusTest
class AuteurResourceTest {
    @InjectSpy
    private AuteurService service;

    @Test
    void shouldFindAllStatusUnauthorizedWhenAnonymous() {
        RestAssured.given()
            .when()
            .get("/auteur")
            .then()
            .statusCode(Status.UNAUTHORIZED.getStatusCode())
        ;
    }

    @Test
    @TestSecurity(user = "user1", roles = "user")
    void shouldFindAllStatusOk() {
        RestAssured.given()
            .when()
            .get("/auteur")
            .then()
            .statusCode(Status.OK.getStatusCode())
            .body("$.size()", CoreMatchers.is(Matchers.greaterThanOrEqualTo(2)))
        ;
    }

    @Test
    @TestSecurity(user = "user1", roles = "user")
    void shouldCreateStatusOk() {
        CreateOrUpdateAuteurRequest request = new CreateOrUpdateAuteurRequest();

        request.setNom("HUGO");
        request.setPrenom("Victor");
        request.setNationalite(NationaliteEnum.FR);

        String id = RestAssured.given()
            .contentType("application/json")
            .body(TestUtil.toJson(request))
            .when()
            .post("/auteur")
            .then()
            .statusCode(Status.CREATED.getStatusCode())
            .extract()
            .path("id")
        ;

        Assertions.assertNotNull(id);

        Mockito.verify(this.service).save(Mockito.any());
    }

    @ParameterizedTest
    @CsvSource({
        "'',Victor,FR",
        ",Victor,FR",
        "Hugo,'',FR",
        "Hugo,,FR",
        "Hugo,Victor,",
        ",,",
    })
    @TestSecurity(user = "user1", roles = "user")
    void shouldCreateStatusBadRequest(String nom, String prenom, NationaliteEnum nat) {
        CreateOrUpdateAuteurRequest request = new CreateOrUpdateAuteurRequest();

        request.setNom(nom);
        request.setPrenom(prenom);
        request.setNationalite(nat);

        RestAssured.given()
            .contentType("application/json")
            .body(TestUtil.toJson(request))
            .when()
            .post("/auteur")
            .then()
            .statusCode(Status.BAD_REQUEST.getStatusCode())
        ;

        Mockito.verify(this.service, Mockito.never()).save(Mockito.any());
    }
}
