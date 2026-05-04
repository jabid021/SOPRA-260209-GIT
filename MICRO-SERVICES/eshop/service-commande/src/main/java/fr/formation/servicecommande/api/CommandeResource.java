package fr.formation.servicecommande.api;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.microprofile.rest.client.inject.RestClient;

import fr.formation.servicecommande.api.dto.request.CreateCommandeDetailRequest;
import fr.formation.servicecommande.api.dto.request.CreateCommandeRequest;
import fr.formation.servicecommande.api.dto.response.CommandeDetailResponse;
import fr.formation.servicecommande.api.dto.response.CommandeResponse;
import fr.formation.servicecommande.clientrest.ClientClientRest;
import fr.formation.servicecommande.clientrest.ProduitClientRest;
import fr.formation.servicecommande.clientrest.StockClientRest;
import fr.formation.servicecommande.clientrest.dto.request.MoveStockRequest;
import fr.formation.servicecommande.clientrest.dto.response.ClientResponse;
import fr.formation.servicecommande.clientrest.dto.response.ProduitResponse;
import fr.formation.servicecommande.model.Commande;
import fr.formation.servicecommande.model.CommandeDetail;
import fr.formation.servicecommande.repo.CommandeRepository;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.Response;

@Path("/api/commande")
public class CommandeResource {
    private final CommandeRepository repository;

    @RestClient
    @Inject
    private ProduitClientRest produitClientRest;

    @RestClient
    @Inject
    private ClientClientRest clientClientRest;

    @RestClient
    @Inject
    private StockClientRest stockClientRest;

    public CommandeResource(CommandeRepository repository) {
        this.repository = repository;
    }

    @GET
    @Transactional
    public List<CommandeResponse> findAll() {
        return this.repository.findAll().stream().map(c -> {
            CommandeResponse resp = CommandeResponse.convert(c);
            ClientResponse client = this.clientClientRest.findById(c.getClientId());

            resp.setClientNom(client.nom());
            resp.setClientPrenom(client.prenom());

            for (CommandeDetail detail : c.getDetails()) {
                resp.getProduits().add(CommandeDetailResponse.convert(detail));
            }

            return resp;
        }).toList();
    }

    @GET
    @Path("/by-client-id/{clientId}")
    public List<String> findAllByClientId(@PathParam("clientId") String clientId) {
        // return this.repository.findAllByClientId(clientId).stream().map(Commande::getId).toList();
        return this.repository.findAllIdsByClientId(clientId);
    }

    @POST
    @Transactional
    public Response create(@Valid CreateCommandeRequest request) {
        Commande commande = new Commande();
        List<CommandeDetail> details = new ArrayList<>();
        BigDecimal total = new BigDecimal(0);

        this.clientClientRest.findById(request.clientId());

        commande.setClientId(request.clientId());

        for (CreateCommandeDetailRequest produitDetail : request.produits()) {
            CommandeDetail detail = new CommandeDetail();

            try {
                ProduitResponse produit = this.produitClientRest.findById(produitDetail.produitId());
                int availableStock = this.stockClientRest.findQuantity(produitDetail.produitId());

                if (availableStock < produitDetail.quantite()) {
                    continue;
                }

                detail.setCommande(commande);
                detail.setProduitId(produitDetail.produitId());
                detail.setProduitLibelle(produit.libelle());
                detail.setProduitPrix(produit.prix());
                detail.setQuantite(produitDetail.quantite());

                total = total.add(produit.prix().multiply(new BigDecimal(produitDetail.quantite())));

                details.add(detail);
                this.stockClientRest.adjustQuantity(produitDetail.produitId(), new MoveStockRequest(-1 * produitDetail.quantite()));
            }

            catch (Exception ex) {

            }
        }

        commande.setDetails(details);
        commande.setTotal(total);

        this.repository.persist(commande);

        return Response.status(Response.Status.CREATED)
            .entity(commande.getId())
            .build()
        ;
    }
}
