package fr.eni.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/Deconnexion")
public class Deconnexion extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final String URL_REDIRECTION = "/LaBonneEnchere2/Accueil";

    public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
    	// Retrieve current session
        HttpSession session = request.getSession();
        // Destroy current session
        session.invalidate();
        // Redirect user to another webpage
        response.sendRedirect(URL_REDIRECTION);
    }
}