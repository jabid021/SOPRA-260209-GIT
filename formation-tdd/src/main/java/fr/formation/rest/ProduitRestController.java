package fr.formation.rest;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.formation.dao.IDAOProduit;
import fr.formation.exception.ProduitNotFoundException;
import fr.formation.model.Produit;
import fr.formation.rest.dto.response.ProduitResponse;
import fr.formation.rest.dto.response.request.CreateProduitRequest;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/produit")
public class ProduitRestController {
    private static Logger log = LoggerFactory.getLogger(ProduitRestController.class);

    // @Autowired / @Inject
    // "final" permet l'immuabilité (pas de nouvelle valeur == constante)
    // "final" oblige à avoir une instance == pas de NullPointer possible
    // "final" ne peut pas s'utiliser avec @Autowired / @Inject => Injection par Constructeur
    private final IDAOProduit daoProduit;

    // Injection de dépendance via Constructeur
    // Le @Autowired est implicite
    public ProduitRestController(IDAOProduit daoProduit) {
        this.daoProduit = daoProduit;
    }

    @GetMapping
    public List<ProduitResponse> findAll() {
        log.debug("Je passe dans le find All");

        return this.daoProduit.findAll()
            .stream()
            .map(ProduitResponse::convert)
            .toList()
        ;
    }

    @GetMapping("/{id}")
    public ProduitResponse findById(@PathVariable int id) {
        log.error("Je passe dans le find by id avec l'id {} - {} - {}", id, "123", "456");

        return this.daoProduit.findById(id)
            .map(ProduitResponse::convert)
            .orElseThrow(ProduitNotFoundException::new)
        ;
    }

    @GetMapping("/by-name/{name}")
    public List<ProduitResponse> findByNom(@PathVariable String name) {
        return this.daoProduit.findByNom(name)
            .stream()
            .map(ProduitResponse::convert)
            .toList()
        ;
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public int create(@Valid @RequestBody CreateProduitRequest request) {
        Produit produit = new Produit();

        produit.setNom(request.getNom());
        produit.setCode(request.getCode());

        this.daoProduit.save(produit);

        return produit.getId();
    }
}
