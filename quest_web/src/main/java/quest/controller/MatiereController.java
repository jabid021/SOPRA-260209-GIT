package quest.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jakarta.servlet.http.HttpSession;
import quest.model.Matiere;

@Controller
public class MatiereController {

	
	@RequestMapping(value="/matiere",method = RequestMethod.POST)
	public String maFonction(String libelle,Integer id,Model model,HttpSession session) 
	{
		Matiere m= new Matiere(id,libelle);
		model.addAttribute("uneData", m);
	//	session.getAttribute("connected");
		session.setAttribute("login", "log-"+libelle);
		System.out.println(m);
		System.out.println("OK");
		return "/WEB-INF/listeDesMatieres.jsp";
	}
	
	
}
