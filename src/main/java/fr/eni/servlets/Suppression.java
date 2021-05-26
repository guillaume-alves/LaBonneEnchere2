package fr.eni.servlets;

import javax.servlet.*;
import javax.servlet.http.*;
import fr.eni.bll.UserManager;
import fr.eni.dao.DAOFactory;
import fr.eni.dao.EnchereDAO;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/Suppression")
public class Suppression extends HttpServlet {
    
	private static final long serialVersionUID 	= 1L;
	public static final String CONF_DAO_FACTORY = "daofactory";
	public static final String ATT_USER 		= "user";
    public static final String ATT_UM 			= "um";
    public static final String VUE 				= "/Deconnexion";
    
    private EnchereDAO enchereDAO;

    public void init() throws ServletException {
        //Getting enchereDAO instance
        this.enchereDAO = ( (DAOFactory) getServletContext().getAttribute(CONF_DAO_FACTORY) ).getEnchereDAO();
    }
    
	@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// Getting instance of BLL
        UserManager um = new UserManager(enchereDAO);
        
        // Calling methods of BLL
        um.deleteUser(request);
		this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
