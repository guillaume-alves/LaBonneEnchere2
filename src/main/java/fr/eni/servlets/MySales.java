package fr.eni.servlets;

import fr.eni.bll.ArticleManager;
import fr.eni.bo.Article;
import fr.eni.dao.DAOFactory;
import fr.eni.dao.EnchereDAO;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/MySales")
public class MySales extends HttpServlet {
    
	private static final long serialVersionUID = 1L;
    public static final String  ATT_AM 				= "am";
    public static final String  ATT_LIST_ARTICLES 	= "list_articles";
    public static final String  CONF_DAO_FACTORY 	= "daofactory";
    public static final String  VUE 				= "/WEB-INF/accueil.jsp";
    private EnchereDAO enchereDAO;

    public void init() throws ServletException {
        //Getting enchereDAO instance
        this.enchereDAO = ( (DAOFactory) getServletContext().getAttribute(CONF_DAO_FACTORY) ).getEnchereDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        listeArticle(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        listeArticle(request, response);
    }

    private void listeArticle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        
    	// Getting instance of BLL
        ArticleManager am = new ArticleManager(enchereDAO);
        
        // Calling methods of BLL
        List<Article> list_articles = am.getListSaleArticles(request);

        // Storage of BLL and objects in the request
        request.setAttribute(ATT_AM, am);
        request.setAttribute(ATT_LIST_ARTICLES, list_articles);
        this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
    }
}
