package biblio.api;

import biblio.dao.IDAOAuteur;
import biblio.model.Auteur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/auteur")
public class AuteurRestController {
    
    @Autowired
    IDAOAuteur daoAuteur;

    @GetMapping("/{id}")
    public Auteur findById(@PathVariable Integer id){
        return daoAuteur.findById(id).orElse(null);
    }

    @GetMapping
    public List<Auteur> findAll(){
        return daoAuteur.findAll();
    }

     @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id)
    {
        daoAuteur.deleteById(id);
    }

    @PostMapping
    public Auteur add(@RequestBody Auteur auteur)
    {
        return daoAuteur.save(auteur);
    }

    @PutMapping("/{id}")
    public Auteur update(@PathVariable Integer id, @RequestBody Auteur auteur)
    {
        auteur.setId(id);
        daoAuteur.save(auteur);
        return auteur;
    }
}