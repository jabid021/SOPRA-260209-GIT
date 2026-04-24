package biblio.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import biblio.model.Avis;
import biblio.model.Livre;
import biblio.service.AvisService;

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

    @GetMapping("/livre/{livreId}")
    public List<Avis> findByLivreId(@PathVariable Integer livreId){
        return avisService.findByLivreId(livreId);
    }
   
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