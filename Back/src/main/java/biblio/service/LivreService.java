package biblio.service;

import biblio.dao.IDAOLivre;
import biblio.model.Editeur;
import biblio.model.Livre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class LivreService {
    @Autowired
    private IDAOLivre daoLivre;

    public List<Livre> findAll()
    {
        return this.daoLivre.findAll();
    }

    public Livre findById(Integer id)
    {
        return this.daoLivre.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Livre introuvable : id=" + id));
    }

    public Livre create(Livre livre)
    {
        if (this.daoLivre.existsByTitre(livre.getTitre()))
        {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Une livre avec ce nom existe déjà");
        }
        return this.daoLivre.save(livre);
    }

    public Livre update(Integer id, Livre livre)
    {
        Livre existing = this.findById(id);
        existing.setTitre(livre.getTitre());
        return this.daoLivre.save(existing);
    }

    public void deleteById(Integer id)
    {
        this.findById(id);
        this.daoLivre.deleteById(id);
    }
}
