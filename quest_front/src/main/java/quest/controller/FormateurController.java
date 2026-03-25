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
import quest.dao.IDAOPersonne;
import quest.model.Formateur;
import quest.model.Genre;


@WebServlet("/formateur")
public class FormateurController extends HttpServlet {

	@Autowired
	IDAOPersonne daoPersonne;
	
	
	public void init(ServletConfig config) throws ServletException
	{
		super.init(config);
		SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this, config.getServletContext());
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("id")==null) 
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
		if(request.getParameter("id")=="") 
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
		Integer id = Integer.parseInt(request.getParameter("id"));
		Formateur formateur = (Formateur) daoPersonne.findById(id).orElse(null);
		List<Formateur> formateurs = daoPersonne.findAllFormateur();
		request.setAttribute("formateur", formateur);
		request.setAttribute("formateurs", formateurs);
		request.setAttribute("civilites", Genre.values());
		this.getServletContext().getRequestDispatcher("/WEB-INF/formateurs.jsp").forward(request, response);
	}

	public void chercherAll(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException  
	{
		List<Formateur> formateurs = daoPersonne.findAllFormateur();
		request.setAttribute("formateur", new Formateur());
		request.setAttribute("formateurs", formateurs);
		request.setAttribute("civilites", Genre.values());

		this.getServletContext().getRequestDispatcher("/WEB-INF/formateurs.jsp").forward(request, response);
	}

	public void supprimer(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException  
	{
		Integer id = Integer.parseInt(request.getParameter("id"));
		daoPersonne.deleteById(id);
		response.sendRedirect("formateur");

	}
	public void ajouter(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException  
	{
		String login = request.getParameter("login");
		String password = request.getParameter("password");
		String nom = request.getParameter("nom");
		String prenom = request.getParameter("prenom");
		String civilite = request.getParameter("civilite");
		boolean admin = (request.getParameter("admin")!=null); //true si la checkbox est coché (donc le form send quelque chose)

		Formateur formateur = new Formateur(login, password, nom, prenom, Genre.valueOf(civilite), admin);
		daoPersonne.save(formateur);

		response.sendRedirect("formateur");
	}

	public void modifier(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException  
	{
		Integer id = Integer.parseInt(request.getParameter("id"));
		String login = request.getParameter("login");
		String password = request.getParameter("password");
		String nom = request.getParameter("nom");
		String prenom = request.getParameter("prenom");
		String civilite = request.getParameter("civilite");
		boolean admin = (request.getParameter("admin")!=null); 

		Formateur formateur = new Formateur(id,login, password, nom, prenom, Genre.valueOf(civilite), admin);
		daoPersonne.save(formateur);

		response.sendRedirect("formateur");
	}


}
