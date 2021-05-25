package fr.eni.servlets;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import fr.eni.dao.DAOFactory;
import fr.eni.dao.EnchereDAO;
import fr.eni.bo.Article;
import fr.eni.bo.Category;
import fr.eni.bll.ArticleManager;

@WebServlet("/Sell")
public class Sell extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final String CONF_DAO_FACTORY = "daofactory";
	public static final String ATT_ARTICLE = "article";
	public static final String ATT_LIST_CATEGORIES = "list_categories";
    public static final String ATT_AM = "am";
    public static 		String VUE = "";
    public static final String VUE_ACCUEIL = "/Accueil";
    public static final String VUE_CREATE_ARTICLE = "/WEB-INF/sell.jsp";
    
    private EnchereDAO enchereDAO;

    public void init() throws ServletException {
        //Getting enchereDAO instance
        this.enchereDAO = ( (DAOFactory) getServletContext().getAttribute(CONF_DAO_FACTORY) ).getEnchereDAO();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
    	  
    	ArticleManager am = new ArticleManager(enchereDAO);
        List<Category> list_categories = null;
  		try {
  			list_categories = am.getListCategories();
  		} catch (Exception e) {
  			// TODO Auto-generated catch block
  			e.printStackTrace();
  		}
  		if (am.getErrors().isEmpty()) {
  			VUE = VUE_ACCUEIL;
  		}
  		else {
  			VUE = VUE_CREATE_ARTICLE;
  		}
    
          request.setAttribute(ATT_AM, am);
          request.setAttribute(ATT_LIST_CATEGORIES, list_categories);
    	this.getServletContext().getRequestDispatcher(VUE_CREATE_ARTICLE).forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
      
    	
        ArticleManager am = new ArticleManager(enchereDAO);
        Article article = null;
        List<Category> list_categories = null;
        
		try {
			article = am.registerArticle(request);
			list_categories = am.getListCategories();			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println(am.getErrors());
		if (am.getErrors().isEmpty()) {
			VUE = VUE_ACCUEIL;
		}
		else {
			VUE = VUE_CREATE_ARTICLE;
		}
  
        request.setAttribute(ATT_AM, am);
        request.setAttribute(ATT_ARTICLE, article);
        request.setAttribute(ATT_LIST_CATEGORIES, list_categories);

        this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
    }
}

