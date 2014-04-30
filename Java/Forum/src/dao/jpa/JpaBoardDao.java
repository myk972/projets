package dao.jpa;

import dao.BoardDao;
import entities.Board;
import entities.Board_;
import entities.Category;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * Created by marc on 01/04/2014.
 */
@Stateless
public class JpaBoardDao implements BoardDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Board getBoardFromTitle(String title) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Board> query = criteriaBuilder.createQuery(Board.class);
        Root<Board> root = query.from(Board.class);
        query.where(criteriaBuilder.equal(root.get(Board_.title), title));
        return entityManager.createQuery(query).getResultList().get(0);
    }

    @Override
    public List<Board> getCategoryBoard(Category category) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Board> query = criteriaBuilder.createQuery(Board.class);
        Root<Board> root = query.from(Board.class);
        query.where(criteriaBuilder.equal(root.get(Board_.category), category));
        return entityManager.createQuery(query).getResultList();
    }

    @Override
    public List<Board> getAllBoards() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Board> criteriaQuery = criteriaBuilder.createQuery(Board.class);
        criteriaQuery.from(Board.class);
        return entityManager.createQuery(criteriaQuery).getResultList();
    }

    @Override
    public void createBoard(Board board) {
        try {
            entityManager.persist(board);
        } catch (PersistenceException e) {
            e.printStackTrace();
        }
    }
}
