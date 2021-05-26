package fr.eni.servlets;

import javax.servlet.*;
import javax.servlet.http.*;
import fr.eni.bll.ArticleManager;
import fr.eni.dao.DAOFactory;
import fr.eni.dao.EnchereDAO;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/CancelSell")
public class CancelSell extends HttpServlet {
    
	private static final long serialVersionUID 	= 1L;
	public static final String CONF_DAO_FACTORY = "daofactory";
	public static final String ATT_USER 		= "user";
    public static final String ATT_UM 			= "um";
    public static final String VUE 				= "/Accueil";
    
    private EnchereDAO enchereDAO;

    public void init() throws ServletException {
        //Getting enchereDAO instance d'une instance
        this.enchereDAO = ( (DAOFactory) getServletContext().getAttribute(CONF_DAO_FACTORY) ).getEnchereDAO();
    }
    
	@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Getting InscriptionManager instance
        ArticleManager am = new ArticleManager(enchereDAO);
        am.deleteArticle(request);
		this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
