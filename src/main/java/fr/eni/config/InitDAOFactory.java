package fr.eni.config;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import fr.eni.dao.DAOFactory;

@WebListener
public class InitDAOFactory implements ServletContextListener {
	private static final String ATT_DAO_FACTORY = "daofactory";
	private DAOFactory daoFactory;

	public void contextInitialized(ServletContextEvent event) {
	    // Getting servletContext
	    ServletContext servletContext = event.getServletContext();
	
	    // Factory instantiation
	    this.daoFactory = DAOFactory.getInstance();
	    
	    // Saving servletContext in attribute with application scope
	    servletContext.setAttribute(ATT_DAO_FACTORY, this.daoFactory);
		}

	public void contextDestroyed(ServletContextEvent event) {
	}
}
