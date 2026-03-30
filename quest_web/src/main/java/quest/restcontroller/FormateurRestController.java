package quest.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import jakarta.servlet.http.HttpSession;
import quest.model.Formateur;
import quest.service.FormateurService;

@RestController
@RequestMapping("/api/formateur")
public class FormateurRestController{

	@Autowired
	FormateurService formateurSrv;

	
	@GetMapping("/{id}")
	public Formateur chercherById(@PathVariable Integer id) 
	{
		Formateur formateur = formateurSrv.getById(id);
		return  null;
	}

	@GetMapping
	public List<Formateur> chercherAll()  
	{
		List<Formateur> formateurs = formateurSrv.getAll();
		return null;
	}

	@DeleteMapping("/{id}")
	public void supprimer(@PathVariable Integer id)  
	{
		formateurSrv.deleteById(id);
	}
	
	@PostMapping
	public Formateur ajouter(@RequestBody Formateur formateur)  
	{
		formateurSrv.insert(formateur);
		return formateur;
	}

	@PutMapping("/{id}")
	public Formateur modifier(@PathVariable Integer id,@RequestBody Formateur formateur)  
	{
		formateur.setId(id);
		try {
			formateurSrv.update(formateur);
		}
		catch(RuntimeException e) 
		{
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cette filiere n'existe pas (id : "+id+")");
		}
		return formateur;
	}

	
	@PatchMapping("/{id}")
	public void modifierIdentifiants(@PathVariable Integer id,@RequestParam String login,String password) 
	{
		Formateur formateur = formateurSrv.getById(id);
		formateur.setLogin(login);
		formateur.setPassword(password);
		formateurSrv.update(formateur);
	}

}
