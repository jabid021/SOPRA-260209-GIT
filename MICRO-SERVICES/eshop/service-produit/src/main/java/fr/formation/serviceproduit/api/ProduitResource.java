package fr.formation.serviceproduit.api;

import java.util.List;

import fr.formation.serviceproduit.api.dto.request.CreateOrUpdateProduitRequest;
import fr.formation.serviceproduit.api.dto.response.ProduitResponse;
import fr.formation.serviceproduit.model.Produit;
import fr.formation.serviceproduit.repo.ProduitRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.Response;

@Path("/api/produit")
public class ProduitResource {
    private final ProduitRepository repository;

    public ProduitResource(ProduitRepository repository) {
        this.repository = repository;
    }

    @GET
    public List<ProduitResponse> findAll() {
        return this.repository.findAll().stream().map(ProduitResponse::convert).toList();
    }

    @GET
    @Path("/{id}")
    public ProduitResponse findById(@PathParam("id") String id) {
        return this.repository.findByIdOptional(id).map(ProduitResponse::convert).orElseThrow(NotFoundException::new);
    }

    @POST
    @Transactional
    public Response create(@Valid CreateOrUpdateProduitRequest request) {
        Produit produit = new Produit();

        produit.setLibelle(request.libelle());
        produit.setPrix(request.prix());

        this.repository.persist(produit);

        return Response.status(Response.Status.CREATED)
            .entity(produit.getId())
            .build()
        ;
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public String update(@PathParam("id") String id, @Valid CreateOrUpdateProduitRequest request) {
        Produit produit = this.repository.findByIdOptional(id).orElseThrow(NotFoundException::new);

        produit.setLibelle(request.libelle());
        produit.setPrix(request.prix());

        this.repository.persist(produit);

        return id;
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public String deleteById(@PathParam("id") String id) {
        this.repository.findByIdOptional(id).orElseThrow(NotFoundException::new);

        return id;
    }
}
