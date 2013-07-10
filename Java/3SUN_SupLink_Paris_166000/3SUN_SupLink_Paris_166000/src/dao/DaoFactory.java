package dao;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class DaoFactory {

    private static final String FICHIER_CONF = "/dao/dao.properties";
    private static final String URL_CONF = "url";
    private static final String Driver_CONF = "driver";
    private static final String CLIENT_CONF = "client";
    private static final String MOT_DE_PASSE_CONF = "mot de passe";

    private String url;
    private String mailClient;
    private String motDePasse;
    private EntityManager em;

    private DaoFactory(String url, String mailClient, String motDePasse, EntityManager em) {
        this.url = url;
        this.mailClient = mailClient;
        this.motDePasse = motDePasse;
        this.em = em;
    };

    public static DaoFactory getInstance() throws DaoConfigurationException {
    	
        Properties properties = new Properties();
        String url;
        String driver;
        String mailClient;
        String motDePasse;

        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        InputStream fichierConf = classLoader.getResourceAsStream(FICHIER_CONF);

        if (fichierConf == null){
            throw new DaoConfigurationException("Le fichier conf " + FICHIER_CONF + "est introuvable");
        }

        try {
            properties.load(fichierConf);
            url = properties.getProperty(URL_CONF);
            driver = properties.getProperty(Driver_CONF);
            mailClient = properties.getProperty(CLIENT_CONF);
            motDePasse = properties.getProperty(MOT_DE_PASSE_CONF);
        } catch (IOException e) {
            throw new DaoConfigurationException("Impossible de charger le ficher conf " + FICHIER_CONF, e);
        }

        try{
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            throw new DaoConfigurationException("Le driver est introuvable", e);
        }
        
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("SupLink-PU");
        EntityManager em = emf.createEntityManager();
        
        DaoFactory daoFactory = new DaoFactory(url, mailClient, motDePasse, em);
        return daoFactory;
    }

    Connection getConnection() throws SQLException{
        return DriverManager.getConnection(url, mailClient, motDePasse);
    }

    public ClientDao CreateClientDao() {
        return new ClientDao(this.em);
    }

    public ShortUmlDao CreateShortUmlDao() {
        return new ShortUmlDao(this.em);
    }
}
