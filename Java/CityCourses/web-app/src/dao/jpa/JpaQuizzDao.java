package dao.jpa;

import dao.QuizzDao;

import javax.ejb.Stateless;
import javax.persistence.*;

/**
 * Created by marc on 26/02/2014.
 */
@Stateless
public class JpaQuizzDao implements QuizzDao {

    @PersistenceContext
    private EntityManager em;
}
