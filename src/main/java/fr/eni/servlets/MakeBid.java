package fr.eni.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.bll.BidManager;
import fr.eni.bo.Bid;
import fr.eni.dao.DAOFactory;
import fr.eni.dao.EnchereDAO;

@WebServlet("/MakeBid")
public class MakeBid extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final String CONF_DAO_FACTORY = "daofactory";
	public static final String ATT_BID = "bid";
    public static final String ATT_BM = "bm";
    public static final String VUE = "/Accueil";
    public static final String ATT_USER_MESSAGE = "userMessage";
    
    private EnchereDAO enchereDAO;

    public void init() throws ServletException {
        //Getting enchereDAO instance
    	this.enchereDAO = ( (DAOFactory) getServletContext().getAttribute(CONF_DAO_FACTORY) ).getEnchereDAO();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        // Getting InscriptionManager instance
        BidManager bm = new BidManager(enchereDAO);
        Bid bid = null;
		try {
			bid = bm.insertBid(request);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		HttpSession session = request.getSession();
		session.removeAttribute(ATT_USER_MESSAGE);
		session.setAttribute(ATT_USER_MESSAGE, bm);
        
		request.setAttribute(ATT_BM, bm);
        request.setAttribute(ATT_BID, bid);

        this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
    }
}

