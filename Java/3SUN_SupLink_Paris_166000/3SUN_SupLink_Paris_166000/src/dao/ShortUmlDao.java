package dao;

import javax.persistence.EntityManager;

import entities.Client;
import entities.ShortUml;

public class ShortUmlDao {

    private EntityManager em;

    public ShortUmlDao(EntityManager em){
        this.em = em;
    }

    public void addShortUml(ShortUml uml) {
    	em.getTransaction().begin();
        try {
            em.persist(uml);
        } catch (Exception e) {
            throw new DaoException(e);
        }
        
        em.getTransaction().commit();
    }

    public void enableUml(ShortUml uml) {
    	uml.setIsEnable(true);
    }

    public void disableUml(ShortUml uml) {
    	uml.setIsEnable(false);
    }
}
