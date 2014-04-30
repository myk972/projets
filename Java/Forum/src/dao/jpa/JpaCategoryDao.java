package dao.jpa;

import dao.CategoryDao;
import entities.Category;
import entities.Category_;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * Created by marc on 31/03/2014.
 */
@Stateless
public class JpaCategoryDao implements CategoryDao {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<Category> getAllCategories() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Category> query = criteriaBuilder.createQuery(Category.class);
        query.from(Category.class);
        return entityManager.createQuery(query).getResultList();
    }

    @Override
    public Category getCategoryFromName(String name) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Category> query = criteriaBuilder.createQuery(Category.class);
        Root<Category> root = query.from(Category.class);
        query.where(criteriaBuilder.equal(root.get(Category_.name), name));
        return entityManager.createQuery(query).getResultList().get(0);
    }

    @Override
    public void createCategory(Category category) {
        try {
            entityManager.persist(category);
        } catch (PersistenceException e) {
            e.printStackTrace();
        }
    }
}
