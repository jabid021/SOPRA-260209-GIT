package biblio.service;

import biblio.dao.IDAOAvis;
import biblio.model.Avis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class AvisService {

    @Autowired
    private IDAOAvis daoAvis;

    public List<Avis> findAll()
    {
        return this.daoAvis.findAll();
    }

    public Avis findById(Integer id)
    {
        return this.daoAvis.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Avis introuvable : id=" + id));
    }

    public Avis create(Avis avis)
    {
        if (this.daoAvis.existsById(avis.getId()))
        {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Un Avis avec ce nom existe déjà");
        }
        return this.daoAvis.save(avis);
    }

    public Avis update(Integer id, Avis avis)
    {
        Avis existing = this.findById(id);
        existing.setId(avis.getId());
        return this.daoAvis.save(existing);
    }

    public void deleteById(Integer id)
    {
        this.findById(id);
        this.daoAvis.deleteById(id);
    }
}
