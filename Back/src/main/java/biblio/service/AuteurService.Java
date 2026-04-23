//Audric
/*
package biblio.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import biblio.dao.IDAOAuteur;
import biblio.model.Auteur;

@Service
public class AuteurService {
    
    @Autowired
    private IDAOAuteur daoAuteur;

    public List<Auteur> findAll()
    {
        return this.daoAuteur.findAll();
    }

    public Auteur findById(Integer id)
    {
        return this.daoAuteur.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Auteur introuvable : id=" + id));
    }

    public Auteur add(Auteur auteur)
    {
        if(this.daoAuteur.existsByNom(auteur.getNom()))
        {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Un auteur avec ce nom existe déjà");
        }
        return this.daoAuteur.save(auteur);
    }

    public Auteur update(Integer id, Auteur auteur)
    {
        Auteur existing = this.findById(id);
        existing.setNom(auteur.getNom());
        return this.daoAuteur.save(existing);
    }

    public void deleteById(Integer id)
    {
        this.findById(id);
        this.daoAuteur.deleteById(id);
    }
}
*/