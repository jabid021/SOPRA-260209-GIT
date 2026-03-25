package quest.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import quest.dao.IDAOFiliere;
import quest.model.Filiere;


@WebServlet("/filiere")
public class FiliereController extends HttpServlet {

	@Autowired
	IDAOFiliere daoFiliere;

	public void init(ServletConfig config) throws ServletException
	{
		super.init(config);
		SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this, config.getServletContext());
	}

	//findById + findAll + delete
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//findAll car pas d'id
		if(request.getParameter("id")==null) 
		{
			chercherAll(request,response);
		}
		else 
		{	//findById
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

	//insert + update
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//insert car pas d'id
		if(request.getParameter("id")=="") 
		{
			ajouter(request,response);
		}
		else 
		{
			modifier(request,response);
		}
	}


	/* On adapte pour chaque model a partir d'ici */

	public void chercherById(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException 
	{
		Integer id = Integer.parseInt(request.getParameter("id"));
		Filiere filiere = daoFiliere.findById(id).orElse(null);
		List<Filiere> filieres = daoFiliere.findAll();

		request.setAttribute("filiere", filiere);
		request.setAttribute("filieres", filieres);
		this.getServletContext().getRequestDispatcher("/WEB-INF/filieres.jsp").forward(request, response);
	}

	public void chercherAll(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException  
	{
		List<Filiere> filieres = daoFiliere.findAll();
		request.setAttribute("filiere", new Filiere());
		request.setAttribute("filieres", filieres);

		this.getServletContext().getRequestDispatcher("/WEB-INF/filieres.jsp").forward(request, response);
	}

	public void supprimer(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException  
	{
		Integer id = Integer.parseInt(request.getParameter("id"));
		daoFiliere.deleteById(id);
		response.sendRedirect("filiere");

	}
	public void ajouter(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException  
	{
		String libelle = request.getParameter("libelle");
		LocalDate debut = LocalDate.parse(request.getParameter("debut"));
		LocalDate fin = LocalDate.parse(request.getParameter("fin"));

		Filiere filiere = new Filiere(libelle,debut,fin);
		daoFiliere.save(filiere);

		response.sendRedirect("filiere");
	}

	public void modifier(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException  
	{
		Integer id = Integer.parseInt(request.getParameter("id"));
		String libelle = request.getParameter("libelle");
		LocalDate debut = LocalDate.parse(request.getParameter("debut"));
		LocalDate fin = LocalDate.parse(request.getParameter("fin"));

		Filiere filiere = new Filiere(id,libelle,debut,fin);
		daoFiliere.save(filiere);

		response.sendRedirect("filiere");
	}


}
