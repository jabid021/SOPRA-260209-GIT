package demo.servlet;
import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;



@WebServlet("/calculette")
public class demoServlet extends HttpServlet {
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException 
	{

		int nb = Integer.parseInt(request.getParameter("nombre"));
		System.out.println("Le carre de nb est "+nb*nb);
		response.getWriter().println("<html>");
		response.getWriter().println("<body>");
		response.getWriter().println("<h1>Calculette - Carre</h1>");
		response.getWriter().println("<p>Le carre de "+nb+" est "+(nb*nb)+"</p>");
		response.getWriter().println("</body>");
		response.getWriter().println("</html>");
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException 
	{
		int nb1 = Integer.parseInt(request.getParameter("number1"));
		int nb2 = Integer.parseInt(request.getParameter("number2"));
		if(request.getParameter("operation").equals("add")) 
		{
			response.getWriter().println("<html>");
			response.getWriter().println("<body>");
			response.getWriter().println("<h1>Calculette - Addition</h1>");
			response.getWriter().println("<p>"+nb1+" + "+nb2+" = "+(nb1+nb2)+"</p>");
			response.getWriter().println("<a href='index.html'>Retour</a>");
			response.getWriter().println("</body>");
			response.getWriter().println("</html>");
		}
		else 
		{
			response.getWriter().println("<html>");
			response.getWriter().println("<body>");
			response.getWriter().println("<h1>Calculette - Soustraction</h1>");
			response.getWriter().println("<p>"+nb1+" - "+nb2+" = "+(nb1-nb2)+"</p>");
			response.getWriter().println("<a href='index.html'>Retour</a>");
			response.getWriter().println("</body>");
			response.getWriter().println("</html>");
		}
	}
	
}
