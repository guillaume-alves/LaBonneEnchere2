package fr.eni.servlets;

import fr.eni.bll.ArticleManager;
import fr.eni.bll.BidManager;
import fr.eni.bo.Article;
import fr.eni.bo.Bid;
import fr.eni.dao.DAOFactory;
import fr.eni.dao.EnchereDAO;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.Date;

@WebServlet("/ArticleProfile")
public class ArticleProfile extends HttpServlet {

	private static final long serialVersionUID = 1L;
	public static final String  CONF_DAO_FACTORY    = "daofactory";
	public static final String  ATT_AM 				= "am";
	public static final String  ATT_BM 				= "bm";
    public static final String  ATT_ARTICLE 		= "article";
    public static final String  ATT_BID 			= "bid";
    public static final String VUE 					= "/WEB-INF/article.jsp";
    private EnchereDAO enchereDAO;

    public void init() throws ServletException {
        //Getting enchereDAO instance
        this.enchereDAO = ( (DAOFactory) getServletContext().getAttribute(CONF_DAO_FACTORY) ).getEnchereDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	ArticleManager am = new ArticleManager(enchereDAO);
        Article article = am.getArticleById(request);
        
        BidManager bm = new BidManager(enchereDAO);
        Bid bid = bm.getBidByArticleId(request);
        
        Date now = new Date();
        
        request.setAttribute(ATT_AM, am);
        request.setAttribute(ATT_ARTICLE, article);
        request.setAttribute(ATT_AM, bm);
        request.setAttribute(ATT_BID, bid);
        request.setAttribute("now", now);
        this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	 this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
    }
}