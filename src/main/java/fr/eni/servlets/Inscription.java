package fr.eni.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import fr.eni.dao.DAOFactory;
import fr.eni.dao.EnchereDAO;
import fr.eni.bo.User;
import fr.eni.bll.UserManager;

@WebServlet("/Inscription")
public class Inscription extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final String CONF_DAO_FACTORY = "daofactory";
	public static final String ATT_USER = "user";
    public static final String ATT_UM = "um";
    public static final String VUE_INSCRIPTION = "/WEB-INF/inscription.jsp";
    public static final String VUE_ACCUEIL = "/Accueil";
    public static 		String VUE = "";
    
    private EnchereDAO enchereDAO;

    public void init() throws ServletException {
        //Getting enchereDAO instance d'une instance
        this.enchereDAO = ( (DAOFactory) getServletContext().getAttribute(CONF_DAO_FACTORY) ).getEnchereDAO();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
    	this.getServletContext().getRequestDispatcher(VUE_INSCRIPTION).forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        // Getting BLL instance
        UserManager um = new UserManager(enchereDAO);

        User user = null;
		try {
			user = um.registerUser(request);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if (um.getErrors().isEmpty()) {
			VUE = VUE_ACCUEIL;
		}
		else {
			VUE = VUE_INSCRIPTION;
		}
		
        // Storage of BLL and the bean in the request
        request.setAttribute(ATT_UM, um);
        request.setAttribute(ATT_USER, user);

        this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
    }
}
