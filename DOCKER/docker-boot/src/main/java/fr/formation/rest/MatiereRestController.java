package fr.formation.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.formation.dao.IDAOMatiere;
import fr.formation.model.Matiere;

@RestController
@RequestMapping("/api/matiere")
public class MatiereRestController {
    @Autowired
    private IDAOMatiere daoMatiere;

    @GetMapping
    public List<Matiere> findAll() {
        return daoMatiere.findAll();
    }

    @PostMapping
    public Matiere create(@RequestBody Matiere matiere) {
        this.daoMatiere.save(matiere);

        return matiere;
    }
}
