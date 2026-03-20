package quest.controller;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import quest.context.Singleton;
import quest.model.Filiere;
import quest.model.Genre;
import quest.model.Ordinateur;
import quest.model.Stagiaire;


@WebServlet("/ordinateur")
public class OrdinateurController extends HttpServlet {

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
		Ordinateur ordinateur = Singleton.getInstance().getDaoOrdinateur().findById(id);
		List<Ordinateur> ordinateurs = Singleton.getInstance().getDaoOrdinateur().findAll();
		List<Stagiaire> stagiaires = Singleton.getInstance().getDaoPersonne().findAllStagiaireDisponibles();

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
		List<Ordinateur> ordinateurs = Singleton.getInstance().getDaoOrdinateur().findAll();
		List<Stagiaire> stagiaires = Singleton.getInstance().getDaoPersonne().findAllStagiaireDisponibles();
		request.setAttribute("ordinateur", new Ordinateur());
		request.setAttribute("ordinateurs", ordinateurs);
		request.setAttribute("stagiaires", stagiaires);

		this.getServletContext().getRequestDispatcher("/WEB-INF/ordinateurs.jsp").forward(request, response);
	}

	public void supprimer(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException  
	{
		Integer id = Integer.parseInt(request.getParameter("id"));
		Singleton.getInstance().getDaoPersonne().deleteById(id);
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
		Singleton.getInstance().getDaoOrdinateur().save(ordinateur);

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
		Singleton.getInstance().getDaoOrdinateur().save(ordinateur);
		
		response.sendRedirect("ordinateur");
	}


}
