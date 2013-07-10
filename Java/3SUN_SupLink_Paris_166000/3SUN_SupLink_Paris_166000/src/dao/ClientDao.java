package dao;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import sun.misc.BASE64Encoder;

import entities.Client;
import entities.ShortUml;

public class ClientDao implements ClientDaoInterface{

    private final EntityManager em;

    public ClientDao(EntityManager em){
        this.em = em;
    }

    @Override
    public void createClient(Client client) throws DaoException{
    	em.getTransaction().begin();
    	
        try {
            em.persist(client);
        } catch (Exception e) {
            throw new DaoException(e);
        }
        
        em.getTransaction().commit();
    }

    @Override
    public Client trouverClient(String email) throws DaoException{
        Client client = null;
        
        try {
            client = em.find(Client.class, email);
        } catch (NoResultException e) {
        } catch (Exception e) {
            throw new DaoException(e);
        }

        return client;
    }

    @Override
    public List<ShortUml> listUml(Client client) {
        return null;
    }
    
    public static String encrypt(String plaintext) throws Exception {
    	 MessageDigest md = null; 
    	
    	 try {
    		 md = MessageDigest.getInstance("SHA"); 
    	 	} catch(NoSuchAlgorithmException e) { 
    	 		throw new Exception(e.getMessage()); 
    	 		} 
    	
    	 try { 
    		 md.update(plaintext.getBytes("UTF-8"));  
    	 } catch(UnsupportedEncodingException e) { 
    		 throw new Exception(e.getMessage()); 
    		 } 
    	
    	 byte raw[] = md.digest();  
    	 String hash = (new BASE64Encoder()).encode(raw);  
    	 return hash; 
    	 }
}

