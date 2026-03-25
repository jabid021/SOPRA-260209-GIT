package quest.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import quest.dao.IDAOOrdinateur;
import quest.dao.IDAOPersonne;
import quest.model.Ordinateur;
import quest.model.Stagiaire;


@WebServlet("/ordinateur")
public class OrdinateurController extends HttpServlet {

	@Autowired
	IDAOOrdinateur daoOrdinateur;
	
	@Autowired
	IDAOPersonne daoPersonne;
	public void init(ServletConfig config) throws ServletException
	{
		super.init(config);
		SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this, config.getServletContext());
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("numero")==null) 
		{
			chercherAll(request,response);
		}
		else 
		{
			if(request.getParameter("delete")==null) 
			{
				chercherById(request,response);
			}
			else 
			{
				supprimer(request,response);
			}
		}

	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("numero")=="") 
		{
			ajouter(request,response);
		}
		else 
		{
			modifier(request,response);
		}
	}



	public void chercherById(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException 
	{
		Integer id = Integer.parseInt(request.getParameter("numero"));
		Ordinateur ordinateur = daoOrdinateur.findById(id).orElse(null);
		List<Ordinateur> ordinateurs = daoOrdinateur.findAll();
		List<Stagiaire> stagiaires = daoPersonne.findAllStagiaireDisponibles();

		if(ordinateur.getUtilisateur()!=null) 
		{
			stagiaires.add(ordinateur.getUtilisateur());
		}
		request.setAttribute("ordinateur", ordinateur);
		request.setAttribute("ordinateurs", ordinateurs);
		request.setAttribute("stagiaires", stagiaires);
		this.getServletContext().getRequestDispatcher("/WEB-INF/ordinateurs.jsp").forward(request, response);
	}

	public void chercherAll(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException  
	{
		List<Ordinateur> ordinateurs = daoOrdinateur.findAll();
		List<Stagiaire> stagiaires = daoPersonne.findAllStagiaireDisponibles();
		request.setAttribute("ordinateur", new Ordinateur());
		request.setAttribute("ordinateurs", ordinateurs);
		request.setAttribute("stagiaires", stagiaires);

		this.getServletContext().getRequestDispatcher("/WEB-INF/ordinateurs.jsp").forward(request, response);
	}

	public void supprimer(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException  
	{
		Integer id = Integer.parseInt(request.getParameter("id"));
		daoPersonne.deleteById(id);
		response.sendRedirect("ordinateur");

	}
	public void ajouter(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException  
	{
		String marque = request.getParameter("marque");
		int ram =Integer.parseInt(request.getParameter("ram"));

		Ordinateur ordinateur = new Ordinateur(marque,ram);
		if(request.getParameter("utilisateur.id")!="") 
		{
			Integer idStagiaire =Integer.parseInt(request.getParameter("utilisateur.id"));
			Stagiaire stagiaire = new Stagiaire();
			stagiaire.setId(idStagiaire);
			ordinateur.setUtilisateur(stagiaire);
		}
		daoOrdinateur.save(ordinateur);

		response.sendRedirect("ordinateur");
	}

	public void modifier(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException  
	{
		Integer id = Integer.parseInt(request.getParameter("numero"));
		String marque = request.getParameter("marque");
		int ram =Integer.parseInt(request.getParameter("ram"));

		Ordinateur ordinateur = new Ordinateur(id,marque,ram);
		if(request.getParameter("utilisateur.id")!="") 
		{
			Integer idStagiaire =Integer.parseInt(request.getParameter("utilisateur.id"));
			Stagiaire stagiaire = new Stagiaire();
			stagiaire.setId(idStagiaire);
			ordinateur.setUtilisateur(stagiaire);
		}
		daoOrdinateur.save(ordinateur);
		
		response.sendRedirect("ordinateur");
	}


}
