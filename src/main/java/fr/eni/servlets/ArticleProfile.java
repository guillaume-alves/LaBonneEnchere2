package fr.eni.servlets;

import fr.eni.bll.ArticleManager;
import fr.eni.bo.Article;
import fr.eni.dao.DAOFactory;
import fr.eni.dao.EnchereDAO;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/ArticleProfile")
public class ArticleProfile extends HttpServlet {


	private static final long serialVersionUID = 1L;
	public static final String  ATT_AM = "am";
    public static final String  ATT_ARTICLES = "article";
    public static final String  CONF_DAO_FACTORY = "daofactory";
    public static final String VUE = "/WEB-INF/article.jsp";
    private EnchereDAO enchereDAO;

    public void init() throws ServletException {
        //Getting enchereDAO instance
        this.enchereDAO = ( (DAOFactory) getServletContext().getAttribute(CONF_DAO_FACTORY) ).getEnchereDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher rd = request.getRequestDispatcher(VUE);
        rd.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ArticleManager am = new ArticleManager(enchereDAO);
        Article article = am.getArticlebyId(request);

        request.setAttribute(ATT_AM, am);
        request.setAttribute(ATT_ARTICLES, article);
        RequestDispatcher rd = request.getRequestDispatcher(VUE);
        rd.forward(request, response);
    }
}