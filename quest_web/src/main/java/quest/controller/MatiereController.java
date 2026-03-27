package quest.controller;

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
import quest.dao.IDAOMatiere;
import quest.model.Matiere;

@Controller
@RequestMapping("/matiere")
public class MatiereController extends HttpServlet {

	@Autowired
	IDAOMatiere daoMatiere;

	
	
	@GetMapping("/recherche")
	public void filtrerMatiere(@RequestParam String recherche) 
	{
		/*List<Matiere> matieresRecherche = daoMatiere.findByLibelleContaining(recherche);
		if(matieresRecherche.isEmpty()) 
		{
			response.getWriter().println("<tr><td align='center' colspan='3'>AUCUNE MATIERE</td></tr>");
		}
		else 
		{
			for(Matiere m : matieresRecherche) 
			{
				response.getWriter().println("<tr><td>"+m.getId()+"</td><td>"+m.getLibelle()+"</td><td><a class='btn btn-warning' href='matiere?id="+m.getId()+"'>Modifier</a><a class='btn btn-danger' href='matiere?id="+m.getId()+"&delete'>Supprimer</a></td></tr>");
			}
		}*/
	}
	
	
	@GetMapping("/{id}")
	public String chercherById(@PathVariable Integer id,Model model) 
	{
		Matiere matiere = daoMatiere.findById(id).orElse(null);
		List<Matiere> matieres = daoMatiere.findAll();
		model.addAttribute("path","/"+id);
		model.addAttribute("matiere", matiere);
		model.addAttribute("matieres", matieres);
		return "matieres.jsp";
	}

	@GetMapping
	public String chercherAll(Model model)  
	{
		List<Matiere> matieres = daoMatiere.findAll();
		model.addAttribute("matiere", new Matiere());
		model.addAttribute("matieres", matieres);

		return "matieres.jsp";
	}

	@GetMapping("/delete/{id}")
	public String supprimer(@PathVariable Integer id)  
	{
		daoMatiere.deleteById(id);
		return "redirect:/matiere";

	}
	
	@PostMapping
	public String ajouter(@RequestParam String libelle)  
	{
		Matiere matiere = new Matiere(libelle);
		daoMatiere.save(matiere);
		return "redirect:/matiere";
	}

	@PostMapping("/{id}")
	public String modifier(@PathVariable Integer id,@RequestParam String libelle,@RequestParam int version)  
	{
	
		Matiere matiere = new Matiere(id,libelle);
		matiere.setVersion(version);
		daoMatiere.save(matiere);
		return "redirect:/matiere";
	}


}
