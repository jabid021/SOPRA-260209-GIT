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

import com.fasterxml.jackson.annotation.JsonView;

import quest.dao.IDAOOrdinateur;
import quest.model.Ordinateur;
import quest.view.Views;

@RestController
@RequestMapping("/api/ordinateur")
public class OrdinateurRestController{

	@Autowired
	IDAOOrdinateur daoOrdinateur;

	
	@GetMapping("/{id}")
	@JsonView(Views.Ordinateur.class)
	public Ordinateur chercherById(@PathVariable Integer id) 
	{
		return daoOrdinateur.findById(id).orElse(null);
	}

	@GetMapping
	@JsonView(Views.Ordinateur.class)
	public List<Ordinateur> chercherAll()  
	{
		return daoOrdinateur.findAll();
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
