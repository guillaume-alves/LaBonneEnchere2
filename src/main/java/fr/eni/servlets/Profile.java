package fr.eni.servlets;

import javax.servlet.*;
import javax.servlet.http.*;
import fr.eni.bll.UserManager;
import fr.eni.bo.User;
import fr.eni.dao.DAOFactory;
import fr.eni.dao.EnchereDAO;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "Profile", value = "/Profile")
public class Profile extends HttpServlet {
    
	private static final long serialVersionUID 	= 1L;
	public static final String CONF_DAO_FACTORY = "daofactory";
	public static final String ATT_USER 		= "user";
    public static final String ATT_UM 			= "um";
    public static final String VUE 				= "/WEB-INF/profile.jsp";
    
    private EnchereDAO enchereDAO;

    public void init() throws ServletException {
        //Getting enchereDAO instance d'une instance
        this.enchereDAO = ( (DAOFactory) getServletContext().getAttribute(CONF_DAO_FACTORY) ).getEnchereDAO();
    }
    
	@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Getting InscriptionManager instance
        UserManager um = new UserManager(enchereDAO);
        User user = um.getUserById(request);

        // Storage of user in the request
        request.setAttribute(ATT_USER, user);
		this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
