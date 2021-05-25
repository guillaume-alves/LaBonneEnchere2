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

@WebServlet("/UpdateProfile")
public class UpdateProfile extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final String CONF_DAO_FACTORY = "daofactory";
	public static final String ATT_USER = "user";
	public static final String ATT_USER_MESSAGE = "userMessage";
    public static final String ATT_UM = "um";
    public static final String VUE = "/WEB-INF/myprofile.jsp";
    public static final String ATT_SESSION_USER = "sessionUser";
    
    private EnchereDAO enchereDAO;

    public void init() throws ServletException {
        //Getting enchereDAO instance d'une instance
        this.enchereDAO = ( (DAOFactory) getServletContext().getAttribute(CONF_DAO_FACTORY) ).getEnchereDAO();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
    	this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        // Getting BLL instance
        UserManager um = new UserManager(enchereDAO);

        User user = null;
		try {
			user = um.updateUser(request);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		HttpSession session = request.getSession();

		if (um.getErrors().isEmpty()) {
			session.removeAttribute(ATT_SESSION_USER);
			session.removeAttribute(ATT_USER_MESSAGE);
			session.setAttribute(ATT_SESSION_USER, user);
			session.setAttribute(ATT_USER_MESSAGE, um);
			
		} else {
			//session.setAttribute(ATT_SESSION_USER, null);
		}
		
        // Storage of BLL and the bean in the request
        request.setAttribute(ATT_UM, um);
        request.setAttribute(ATT_USER, user);

        this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
    }
}

