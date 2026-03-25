package quest.controller;

import java.io.IOException;
import java.util.ArrayList;
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
import quest.model.Personne;
import quest.model.Stagiaire;


@WebServlet("/login")
public class LoginController extends HttpServlet {

	@Autowired
	IDAOPersonne daoPersonne;
	public void init(ServletConfig config) throws ServletException
	{
		super.init(config);
		SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this, config.getServletContext());
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("logout")!=null) 
		{
			request.getSession().invalidate();
			response.sendRedirect("home");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String login = request.getParameter("login");
		String password = request.getParameter("password");
		Personne personne = daoPersonne.findByLoginAndPassword(login, password);
		if(personne==null) 
		{
			response.sendRedirect("home?error");
		}
		else {
			request.getSession().setAttribute("connected", personne);
			List<String> roles = new ArrayList();
			if(personne instanceof Stagiaire) 
			{
				roles.add("ROLE_STAGIAIRE");
			}
			else if(personne instanceof Formateur) 
			{
				roles.add("ROLE_FORMATEUR");
				if(((Formateur) personne).isAdmin()) 
				{
					roles.add("ROLE_ADMIN");
				}
			}
			request.getSession().setAttribute("roles", roles);
			response.sendRedirect("home");
		}
	}

}
