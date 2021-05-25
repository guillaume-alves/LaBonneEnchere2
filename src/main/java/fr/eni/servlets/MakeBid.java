package fr.eni.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import fr.eni.dao.DAOFactory;
import fr.eni.dao.EnchereDAO;
import fr.eni.bo.Bid;
import fr.eni.bll.BidManager;

@WebServlet("/makebid")
public class MakeBid extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final String CONF_DAO_FACTORY = "daofactory";
	public static final String ATT_BID = "bid";
    public static final String ATT_BM = "bm";
    public static final String VUE = "";
    
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
			bid = bm.registerBid(request);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        /* Stockage du formulaire et du bean dans l'objet request */
        request.setAttribute(ATT_BM, bm);
        request.setAttribute(ATT_BID, bid);

        this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
    }
}

