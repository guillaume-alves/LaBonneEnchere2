package fr.eni.dao;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DAOFactory {

    private static final String FICHIER_PROPERTIES       = "/fr/eni/dao/DAO.properties";
    private static final String PROPERTY_URL             = "url";
    private static final String PROPERTY_DRIVER          = "driver";
    private static final String PROPERTY_NOM_UTILISATEUR = "usr";
    private static final String PROPERTY_MOT_DE_PASSE    = "mdp";

	private String url;
	private String username;
	private String password;

    DAOFactory(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }

    /* M�thode charg�e de r�cup�rer les informations de connexion � la base de
       donn�es, charger le driver JDBC et retourner une instance de la Factory */
    public static DAOFactory getInstance() throws DAOConfigurationException {
        Properties properties = new Properties();
        String url;
        String driver;
        String usr;
        String mdp;

        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        InputStream fichierProperties = classLoader.getResourceAsStream( FICHIER_PROPERTIES );

        if (fichierProperties == null) {
            throw new DAOConfigurationException("Le fichier properties " + FICHIER_PROPERTIES + " est introuvable.");
        }

        try {
            properties.load(fichierProperties);
            url = properties.getProperty(PROPERTY_URL);
            driver = properties.getProperty(PROPERTY_DRIVER);
            usr = properties.getProperty(PROPERTY_NOM_UTILISATEUR);
            mdp = properties.getProperty(PROPERTY_MOT_DE_PASSE);
        } catch (IOException e) {
            throw new DAOConfigurationException("Impossible de charger le fichier properties " + FICHIER_PROPERTIES, e);
        }

        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            throw new DAOConfigurationException( "Le driver est introuvable dans le classpath.", e );
        }

        DAOFactory instance = new DAOFactory(url, usr, mdp);
        return instance;
    }

    /* M�thode charg�e de fournir une connexion � la base de donn�es*/
    	Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, username, password);
    }

    /* M�thodes de r�cup�ration de l'impl�mentation des diff�rents DAO*/
    public EnchereDAO getEnchereDAO() {
        return new EnchereDAOImpl(this);
    }
}
