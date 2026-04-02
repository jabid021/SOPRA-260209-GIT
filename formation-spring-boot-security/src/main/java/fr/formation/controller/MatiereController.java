package fr.formation.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.formation.dao.IDAOMatiere;
import fr.formation.model.Matiere;

@RestController
@RequestMapping("/matiere")
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

    // @DeleteMapping
    // @PreAuthorize("hasAuthority('MATIERE_DELETE')")
    // public void deleteById() {

    // }
}
