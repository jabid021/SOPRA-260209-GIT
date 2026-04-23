// MARTIN

package biblio.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import biblio.model.Collection;
import biblio.service.CollectionService;

@RestController
@RequestMapping("/api/collection")
@CrossOrigin(origins = "http://localhost:4200")
public class CollectionController 
{
    @Autowired
    private CollectionService collectionService;

    @GetMapping
    public List<Collection> findAll() 
    {
        return this.collectionService.findAll();
    }

    @GetMapping("/{id}")
    public Collection findById(@PathVariable Integer id) 
    {
        return this.collectionService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Collection add(@RequestBody Collection collection) 
    {
        return this.collectionService.add(collection);
    }

    @PutMapping("/{id}")
    public Collection update(@PathVariable Integer id, @RequestBody Collection collection) 
    {
        return this.collectionService.update(id, collection);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Integer id) 
    {
        this.collectionService.deleteById(id);
    }
}
