package fr.formation.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.formation.dao.IDAOMatiere;
import fr.formation.model.Matiere;

@RestController
@RequestMapping("/api/matiere")
@PreAuthorize("hasRole('ADMIN')")
// @PreAuthorize("hasAuthority('ROLE_ADMIN')")
public class MatiereController {
    @Autowired
    private IDAOMatiere daoMatiere;

    @GetMapping
    public List<Matiere> findAll(Authentication auth) {
        System.out.println(auth.getAuthorities());

        return daoMatiere.findAll();
    }

    @PostMapping
    public Matiere create(@RequestBody Matiere matiere) {
        this.daoMatiere.save(matiere);

        return matiere;
    }

    // @DeleteMapping
    // @PreAuthorize("hasAuthority('MATIERE_DELETE')")
    // public void deleteById() {

    // }
}
