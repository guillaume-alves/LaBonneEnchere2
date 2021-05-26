package fr.eni.servlets;

import javax.servlet.*;
import javax.servlet.http.*;

import fr.eni.bll.UserManager;
import fr.eni.bo.User;
import fr.eni.dao.DAOFactory;
import fr.eni.dao.EnchereDAO;

import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/CleanMessages")
public class CleanMessages extends HttpServlet {
    
	private static final long serialVersionUID 		= 1L;
    public static final String  URL_REDIRECTION 	= "/LaBonneEnchere2/Accueil";
    public static final String 	ATT_USER_MESSAGE 	= "userMessage";
    public static final String  CONF_DAO_FACTORY 	= "daofactory";
    public static final String  ATT_SESSION_USER 	= "sessionUser";
	private EnchereDAO enchereDAO;

    public void init() throws ServletException {
        //Getting enchereDAO instance d'une instance
        this.enchereDAO = ( (DAOFactory) getServletContext().getAttribute(CONF_DAO_FACTORY) ).getEnchereDAO();
    }
    
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	UserManager um = new UserManager(enchereDAO);
		User user = null;
		try {
			user = um.getUserById(request);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
    	// Retrieve current session
        HttpSession session = request.getSession();
        
        // Remove messages in the current session
        session.removeAttribute(ATT_USER_MESSAGE);
        
        if (um.getErrors().isEmpty()) {
			session.setAttribute(ATT_SESSION_USER, user);
		
			// Redirect user to another webpage
        response.sendRedirect(URL_REDIRECTION);
     }
   }
}
