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
import quest.dao.IDAOMatiere;
import quest.model.Matiere;


@WebServlet("/matiere")
public class MatiereController extends HttpServlet {

	@Autowired
	IDAOMatiere daoMatiere;
	
	
	public void init(ServletConfig config) throws ServletException
	{
		super.init(config);
		SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this, config.getServletContext());
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(request.getParameter("id")==null) 
		{
			if(request.getParameter("recherche")==null) 
			{
				chercherAll(request,response);
			}
			else 
			{
				String recherche = request.getParameter("recherche");
				List<Matiere> matieresRecherche = daoMatiere.findByLibelleContaining(recherche);
				
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
				}
			}
			
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
		Matiere matiere = daoMatiere.findById(id).orElse(null);
		List<Matiere> matieres = daoMatiere.findAll();
		
		request.setAttribute("matiere", matiere);
		request.setAttribute("matieres", matieres);
		this.getServletContext().getRequestDispatcher("/WEB-INF/matieres.jsp").forward(request, response);
	}
	
	public void chercherAll(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException  
	{
		List<Matiere> matieres = daoMatiere.findAll();
		request.setAttribute("matiere", new Matiere());
		request.setAttribute("matieres", matieres);
		
		this.getServletContext().getRequestDispatcher("/WEB-INF/matieres.jsp").forward(request, response);
	}
	
	public void supprimer(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException  
	{
		Integer id = Integer.parseInt(request.getParameter("id"));
		daoMatiere.deleteById(id);
		response.sendRedirect("matiere");
		
	}
	public void ajouter(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException  
	{
		String libelle = request.getParameter("libelle");
		
		Matiere matiere = new Matiere(libelle);
		daoMatiere.save(matiere);
		
		response.sendRedirect("matiere");
	}

	public void modifier(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException  
	{
		Integer id = Integer.parseInt(request.getParameter("id"));
		String libelle = request.getParameter("libelle");
		Integer version = Integer.parseInt(request.getParameter("version"));
		Matiere matiere = new Matiere(id,libelle);
		matiere.setVersion(version);
		daoMatiere.save(matiere);
		
		response.sendRedirect("matiere");
	}
	

}
