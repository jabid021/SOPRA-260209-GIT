package quest.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpServlet;
import quest.dao.IDAOFiliere;
import quest.model.Filiere;

@Controller
@RequestMapping("/filiere")
public class FiliereController extends HttpServlet {

	@Autowired
	IDAOFiliere daoFiliere;

	
	@GetMapping("/{id}")
	public String chercherById(@PathVariable Integer id,Model model) 
	{
		Filiere filiere = daoFiliere.findById(id).orElse(null);
		List<Filiere> filieres = daoFiliere.findAll();

		model.addAttribute("filiere", filiere);
		model.addAttribute("filieres", filieres);
		return "/WEB-INF/filieres.jsp";
	}

	@GetMapping
	public String chercherAll(Model model)  
	{
		List<Filiere> filieres = daoFiliere.findAll();
		model.addAttribute("filiere", new Filiere());
		model.addAttribute("filieres", filieres);

		return "/WEB-INF/filieres.jsp";
	}

	@GetMapping("/delete/{id}")
	public String supprimer(@PathVariable Integer id)  
	{
		daoFiliere.deleteById(id);
		return "redirect:/filiere";

	}
	
	@PostMapping
	public String ajouter(@RequestParam String libelle,@RequestParam LocalDate debut,@RequestParam LocalDate fin)  
	{
		Filiere filiere = new Filiere(libelle,debut,fin);
		daoFiliere.save(filiere);
		return "redirect:/filiere";
	}

	@PostMapping("/{id}")
	public String modifier(@PathVariable Integer id,@RequestParam String libelle,@RequestParam LocalDate debut,@RequestParam LocalDate fin)  
	{
	
		Filiere filiere = new Filiere(id,libelle,debut,fin);
		daoFiliere.save(filiere);
		return "redirect:/filiere";
	}


}
