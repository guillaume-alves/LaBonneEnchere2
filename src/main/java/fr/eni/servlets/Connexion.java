package fr.eni.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import fr.eni.bll.UserManager;
import fr.eni.bo.User;
import fr.eni.dao.DAOFactory;
import fr.eni.dao.EnchereDAO;

@WebServlet("/Connexion")
public class Connexion extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final String CONF_DAO_FACTORY = "daofactory";
	public static final String ATT_USER = "user";
	public static final String ATT_UM = "um";
	public static final String ATT_SESSION_USER = "sessionUser";
	public static String VUE = "";
	public static final String VUE_ACCUEIL = "/Accueil";
	public static final String VUE_CONNEXION = "/WEB-INF/connexion.jsp";
	
	private EnchereDAO enchereDAO;

	public void init() throws ServletException {
        //Getting enchereDAO instance d'une instance
        this.enchereDAO = ( (DAOFactory) getServletContext().getAttribute(CONF_DAO_FACTORY) ).getEnchereDAO();
    }
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.getServletContext().getRequestDispatcher(VUE_CONNEXION).forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		UserManager um = new UserManager(enchereDAO);

		User user = null;
		try {
			user = um.connectUser(request);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		HttpSession session = request.getSession();

		if (um.getErrors().isEmpty()) {
			session.setAttribute(ATT_SESSION_USER, user);
			VUE = VUE_ACCUEIL;
		} else {
			session.setAttribute(ATT_SESSION_USER, null);
			VUE = VUE_CONNEXION;
		}

		request.setAttribute(ATT_UM, um);
		request.setAttribute(ATT_USER, user);

		this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
	}
}
