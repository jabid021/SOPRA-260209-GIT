package fr.formation;

import fr.formation.musique.Guitariste;
import fr.formation.request.HelloRequest;
import fr.formation.response.ExempleRecordResponse;
import fr.formation.response.ExempleResponse;
import jakarta.ws.rs.BeanParam;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;

@Path("/hello")
public class GreetingResource {
    private final Guitariste guitariste;

    // Injection de dépendance par constructeur
    public GreetingResource(Guitariste guitariste) {
        this.guitariste = guitariste;
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        this.guitariste.jouer();

        return "Hello from Quarkus REST (2)";
    }

    @GET
    @Path("/demo")
    public String demo() {
        return "Démonstration ";
    }

    @GET
    @Path("/exemple")
    public ExempleResponse exemple() {
        return new ExempleResponse("Le message");
    }

    @GET
    @Path("/exemple-record")
    public ExempleRecordResponse exempleRecord() {
        return new ExempleRecordResponse("Le message");
    }

    @GET
    @Path("/query-param")
    public String queryParam(@QueryParam("id") Integer id) {
        return "Le paramètre = " + id;
    }

    @GET
    @Path("/path-param/{id}")
    public String pathParam(@PathParam("id") Integer id) {
        return "Le paramètre (path param) = " + id;
    }

    @GET
    @Path("/bean-param")
    public String beanParam(@BeanParam HelloRequest request) {
        return "Le message est " + request.getMessage();
    }
}
