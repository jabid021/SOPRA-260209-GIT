package fr.formation.api;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import fr.formation.model.Produit;
import fr.formation.request.CreateOrUpdateProduitRequest;
import fr.formation.request.CreateProduitRequest;
import fr.formation.response.ProduitResponse;
import jakarta.ws.rs.BeanParam;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;

@Path("/api/produit")
public class ProduitResource {
    private List<Produit> produits = new ArrayList<>();
    private int lastId = 2;

    public ProduitResource() {
        this.produits.add(new Produit(1, "Parachute", new BigDecimal("8500")));
        this.produits.add(new Produit(2, "Casque de moto", new BigDecimal("499.50")));
    }

    @GET
    public List<ProduitResponse> findAll() {
        return this.produits.stream().map(ProduitResponse::convert).toList();
    }

    @POST
    @Path("/create")
    public int createBeanParam(@BeanParam CreateProduitRequest request) {
        Produit produit = new Produit();

        produit.setId(++this.lastId);
        produit.setLibelle(request.getLibelle());
        produit.setPrix(request.getPrix());

        this.produits.add(produit);

        return this.lastId;
    }

    @POST
    public int create(CreateOrUpdateProduitRequest request) {
        Produit produit = new Produit();

        produit.setId(++this.lastId);
        produit.setLibelle(request.getLibelle());
        produit.setPrix(request.getPrix());

        this.produits.add(produit);

        return this.lastId;
    }

    @PUT
    @Path("/{id}")
    public int update(@PathParam("id") Integer id, CreateOrUpdateProduitRequest request) {
        Produit produit = this.produits.stream().filter(p -> p.getId() == id).findFirst().orElseThrow();

        produit.setLibelle(request.getLibelle());
        produit.setPrix(request.getPrix());

        return id;
    }

    @DELETE
    @Path("/{id}")
    public int deleteById(@PathParam("id") Integer id) {
        this.produits.removeIf(p -> p.getId() == id);

        return id;
    }
}
