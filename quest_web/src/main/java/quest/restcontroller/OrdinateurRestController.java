package quest.restcontroller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import quest.dao.IDAOOrdinateur;
import quest.model.Ordinateur;

@RestController
@RequestMapping("/api/ordinateur")
public class OrdinateurRestController{

	@Autowired
	IDAOOrdinateur daoOrdinateur;

	
	@GetMapping("/{id}")
	public Ordinateur chercherById(@PathVariable Integer id) 
	{
		Ordinateur ordinateur = daoOrdinateur.findById(id).orElse(null);
		return  null;
	}

	@GetMapping
	public List<Ordinateur> chercherAll()  
	{
		List<Ordinateur> ordinateurs = daoOrdinateur.findAll();
		return null;
	}

	@DeleteMapping("/{id}")
	public void supprimer(@PathVariable Integer id)  
	{
		daoOrdinateur.deleteById(id);
	}
	
	@PostMapping
	public Ordinateur ajouter(@RequestBody Ordinateur ordinateur)  
	{
		daoOrdinateur.save(ordinateur);
		return ordinateur;
	}

	@PutMapping("/{id}")
	public Ordinateur modifier(@PathVariable Integer id,@RequestBody Ordinateur ordinateur)  
	{
		
		ordinateur.setNumero(id);
		daoOrdinateur.save(ordinateur);
		return ordinateur;
	}


}
