// MARTIN
package biblio.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import biblio.dao.IDAOCollection;
import biblio.model.Collection;

@Service
public class CollectionService 
{
    @Autowired
    private IDAOCollection daoCollection;

    public List<Collection> findAll() 
    {
        return this.daoCollection.findAll();
    }

    public Collection findById(Integer id) 
    {
        return this.daoCollection.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Collection introuvable : id=" + id));
    }

    public Collection add(Collection collection) 
    {
        if (this.daoCollection.existsByNom(collection.getNom())) 
        {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Une collection avec ce nom existe déjà");
        }
        return this.daoCollection.save(collection);
    }

    public Collection update(Integer id, Collection collection) 
    {
        Collection existing = this.findById(id);
        existing.setNom(collection.getNom());
        return this.daoCollection.save(existing);
    }

    public void deleteById(Integer id) 
    {
        this.findById(id);
        this.daoCollection.deleteById(id);
    }
}
