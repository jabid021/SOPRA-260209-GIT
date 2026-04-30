package fr.formation.api;

import java.util.List;

import fr.formation.model.Produit;
import fr.formation.repository.ProduitRepository;
import fr.formation.request.CreateOrUpdateProduitRequest;
import fr.formation.request.CreateProduitRequest;
import fr.formation.response.ProduitResponse;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.BeanParam;
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
    public ProduitResponse findById(@PathParam("id") Integer id) {
        return ProduitResponse.convert(this.repository.findByIdOptional(id).orElseThrow(NotFoundException::new));
    }

    @Transactional
    @POST
    @Path("/create")
    public int createBeanParam(@BeanParam CreateProduitRequest request) {
        Produit produit = new Produit();

        produit.setLibelle(request.getLibelle());
        produit.setPrix(request.getPrix());

        this.repository.persist(produit);

        return produit.getId();
    }

    @Transactional
    @POST
    public Response create(CreateOrUpdateProduitRequest request) {
        Produit produit = new Produit();

        produit.setLibelle(request.getLibelle());
        produit.setPrix(request.getPrix());

        this.repository.persist(produit);

        return Response.status(Response.Status.CREATED)
            .entity(produit.getId())
            .build()
        ;
    }

    @Transactional
    @PUT
    @Path("/{id}")
    public int update(@PathParam("id") Integer id, CreateOrUpdateProduitRequest request) {
        Produit produit = this.repository.findByIdOptional(id).orElseThrow(NotFoundException::new);

        produit.setLibelle(request.getLibelle());
        produit.setPrix(request.getPrix());

        this.repository.persist(produit);

        return id;
    }

    @Transactional
    @DELETE
    @Path("/{id}")
    public int deleteById(@PathParam("id") Integer id) {
        this.repository.deleteById(id);

        return id;
    }
}
