package fr.formation.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.formation.dao.IDAOMatiere;
import fr.formation.model.Matiere;

@RestController
@RequestMapping("/matiere")
@PreAuthorize("hasRole('ADMIN')")
public class MatiereController {
    @Autowired
    private IDAOMatiere daoMatiere;

    @GetMapping
    public List<Matiere> findAll() {
        return daoMatiere.findAll();
    }
}
