package biblio.api;

import biblio.dao.IDAOAvis;
import biblio.model.Avis;
import biblio.model.Livre;

import biblio.service.AvisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/avis")
@CrossOrigin(origins = "http://localhost:4200")
public class AvisRestController {
    
    @Autowired
    private AvisService avisService;

    @GetMapping("/{id}")
    public Avis findById(@PathVariable Integer id){
        return avisService.findById(id);
    }

    /* 
    @GetMapping("/livre/{titre}")
    public List<Livre> findByLivre(@PathVariable String titre){
        return daoAvis.findByLivre(titre);
    }
    */
   
    @GetMapping
    public List<Avis> findAll(){
        return avisService.findAll();
    }

     @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id)
    {
        avisService.deleteById(id);
    }

    @PostMapping
    public Avis add(@RequestBody Avis avis)
    {
        return avisService.create(avis);
    }

    @PutMapping("/{id}")
    public Avis update(@PathVariable Integer id, @RequestBody Avis avis)
    {
        avis.setId(id);
        avisService.create(avis);
        return avis;
    }
}