package quest.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;

import quest.dao.IDAOMatiere;
import quest.model.Matiere;
import quest.view.Views;

@RestController
@RequestMapping("/api/matiere")
public class MatiereRestController{

	@Autowired
	IDAOMatiere daoMatiere;

	
	@GetMapping("/{id}")
	@JsonView(Views.Matiere.class)
	public Matiere chercherById(@PathVariable Integer id) 
	{
		return daoMatiere.findById(id).orElse(null);
	}

	@GetMapping
	@JsonView(Views.Matiere.class)
	public List<Matiere> chercherAll()  
	{
		return daoMatiere.findAll();
	}

	@DeleteMapping("/{id}")
	public void supprimer(@PathVariable Integer id)  
	{
		daoMatiere.deleteById(id);
	}
	
	@PostMapping
	public Matiere ajouter(@RequestBody Matiere matiere)  
	{
		daoMatiere.save(matiere);
		return matiere;
	}

	@PutMapping("/{id}")
	public Matiere modifier(@PathVariable Integer id,@RequestBody Matiere matiere)  
	{
		matiere.setId(id);
		daoMatiere.save(matiere);
		return matiere;
	}


}
