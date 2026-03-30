package quest.restcontroller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import quest.model.Matiere;

@RestController
@RequestMapping("/api/matiere")
public class MatiereRestController {

	@GetMapping("/{id}")
	public Matiere chercherById(@PathVariable Integer id) 
	{
		Matiere matiere = new Matiere();
		matiere.setId(id);
		return matiere;
	}

	@GetMapping
	public List<Matiere> chercherAll()
	{
		List<Matiere> matieres = new ArrayList();
		return matieres;
	}
	
}
