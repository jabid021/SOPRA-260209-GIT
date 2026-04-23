package biblio.service;

import biblio.dao.IDAOEditeur;
import biblio.model.Editeur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class EditeurService {

    @Autowired
    private IDAOEditeur daoEditeur;

    public List<Editeur> findAll() 
    {
        return this.daoEditeur.findAll();
    }

    public Editeur findById(Integer id) 
    {
        return this.daoEditeur.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Editeur introuvable : id=" + id));
    }

    public Editeur create(Editeur editeur) 
    {
        if (this.daoEditeur.existsByNom(editeur.getNom())) 
        {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Une editeur avec ce nom existe déjà");
        }
        return this.daoEditeur.save(editeur);
    }

    public Editeur update(Integer id, Editeur editeur) 
    {
        Editeur existing = this.findById(id);
        existing.setNom(editeur.getNom());
        return this.daoEditeur.save(existing);
    }

    public void deleteById(Integer id) 
    {
        this.findById(id);
        this.daoEditeur.deleteById(id);
    }
    
}
