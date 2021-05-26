package fr.eni.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.bll.BidManager;
import fr.eni.bll.UserManager;
import fr.eni.bo.Bid;
import fr.eni.bo.User;
import fr.eni.dao.DAOFactory;
import fr.eni.dao.EnchereDAO;

@WebServlet("/MakeBid")
public class MakeBid extends HttpServlet {
	private static final long serialVersionUID 	= 1L;
	public static final String CONF_DAO_FACTORY = "daofactory";
	public static final String ATT_BID 			= "bid";
    public static final String ATT_BM 			= "bm";
    public static final String VUE 				= "/Accueil";
    public static final String ATT_USER_MESSAGE = "userMessage";
    public static final String ATT_SESSION_USER = "sessionUser";
    
    private EnchereDAO enchereDAO;

    public void init() throws ServletException {
        //Getting enchereDAO instance
    	this.enchereDAO = ( (DAOFactory) getServletContext().getAttribute(CONF_DAO_FACTORY) ).getEnchereDAO();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
    	this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
    	
    	// Getting instance of BLL
        BidManager bm = new BidManager(enchereDAO);
        UserManager um = new UserManager(enchereDAO);
        User user = null;
        Bid bid = null;
		
        // Calling methods of BLL
        try {
			bid = bm.insertBid(request);
			user = um.getUserById(request);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
        // Retrieve current session and delete content
		HttpSession session = request.getSession();
		session.removeAttribute(ATT_USER_MESSAGE);
		session.removeAttribute(ATT_SESSION_USER);
		
		// Storage of user bean in the session
		session.setAttribute(ATT_USER_MESSAGE, bm);
		session.setAttribute(ATT_SESSION_USER, user);
        
		// Storage of user bean in the request
		request.setAttribute(ATT_BM, bm);
        request.setAttribute(ATT_BID, bid);

        this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
    }
}

