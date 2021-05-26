package fr.eni.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.dao.DAOFactory;
import fr.eni.dao.EnchereDAO;
import fr.eni.bo.User;
import fr.eni.bll.UserManager;

@WebServlet("/Inscription")
public class Inscription extends HttpServlet {
	private static final long serialVersionUID 	= 1L;
	public static final String CONF_DAO_FACTORY = "daofactory";
	public static final String ATT_USER 		= "user";
    public static final String ATT_UM			= "um";
    public static final String ATT_USER_MESSAGE = "userMessage";
    public static final String ATT_SESSION_USER = "sessionUser";
    public static final String VUE_INSCRIPTION 	= "/WEB-INF/inscription.jsp";
    public static final String VUE_ACCUEIL 		= "/Accueil";
    public static 		String VUE 				= "";
    
    private EnchereDAO enchereDAO;

    public void init() throws ServletException {
        //Getting enchereDAO instance
        this.enchereDAO = ( (DAOFactory) getServletContext().getAttribute(CONF_DAO_FACTORY) ).getEnchereDAO();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
    	this.getServletContext().getRequestDispatcher(VUE_INSCRIPTION).forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        
    	// Getting BLL instance
        UserManager um = new UserManager(enchereDAO);
        User user = null;
		
        // Calling methods of BLL
        try {
			user = um.registerUser(request);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
        // Connect user is registration succeed
		if (um.getErrors().isEmpty()) {
			try {
				user = um.connectUser(request);
			} catch (Exception e) {
				e.printStackTrace();
			}
		
		// Storage of user bean in the session
		HttpSession session = request.getSession();
		session.setAttribute(ATT_SESSION_USER, user);
		session.removeAttribute(ATT_USER_MESSAGE);
		session.setAttribute(ATT_USER_MESSAGE, um);
		VUE = VUE_ACCUEIL;
		}
		else {
		
		// Storage of user bean in the request
		request.setAttribute(ATT_UM, um);
		request.setAttribute(ATT_USER, user);
		VUE = VUE_INSCRIPTION;
		}
		
        this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
    }
}

