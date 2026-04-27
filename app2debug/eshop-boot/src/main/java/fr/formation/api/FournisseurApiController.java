package fr.formation.api;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import fr.formation.request.CreateFournisseurRequest;
import fr.formation.response.FournisseurResponse;
import fr.formation.service.FournisseurService;

@RestController
@RequestMapping("/api/fournisseur")
public class FournisseurApiController {
    private FournisseurService service = new FournisseurService(null);

    @GetMapping
    public List<FournisseurResponse> findAll() {
        return this.service.findAll()
            .stream()
            .map(FournisseurResponse::convert)
            .toList()
        ;
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    public Integer create(@RequestBody CreateFournisseurRequest request) {
        return this.service.save(null, request).getId();
    }
}
