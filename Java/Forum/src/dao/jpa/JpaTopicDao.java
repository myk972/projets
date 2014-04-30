package dao.jpa;

import dao.TopicDao;
import entities.Board;
import entities.Message;
import entities.Topic;
import entities.Topic_;

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
public class JpaTopicDao implements TopicDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Topic getTopicFromTitle(String title) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Topic> query = criteriaBuilder.createQuery(Topic.class);
        Root<Topic> root = query.from(Topic.class);
        query.where(criteriaBuilder.equal(root.get(Topic_.title), title));
        return entityManager.createQuery(query).getResultList().get(0);
    }

    @Override
    public List<Topic> getBoardTopics(Board board) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Topic> query = criteriaBuilder.createQuery(Topic.class);
        Root<Topic> root = query.from(Topic.class);
        query.where(criteriaBuilder.equal(root.get(Topic_.board), board));
        return entityManager.createQuery(query).getResultList();
    }

    @Override
    public void changeTopicLock(Topic topic) {
        Topic savedTopic = getTopic(topic.getId());
        savedTopic.setLocked(!savedTopic.isLocked());
    }

    @Override
    public List<Topic> getAllTopics() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Topic> query = criteriaBuilder.createQuery(Topic.class);
        query.from(Topic.class);
        return entityManager.createQuery(query).getResultList();
    }

    @Override
    public void deleteTopic(Topic topic) {
        Topic removedTopic = getTopic(topic.getId());
        entityManager.remove(removedTopic);
    }

    @Override
    public void addMessages(Topic destinationTopic, List<Message> messages) {
        Topic topic = getTopic(destinationTopic.getId());
        topic.getContent().addAll(messages);
        entityManager.merge(topic);
    }

    @Override
    public void updateTopic(Topic topic, Board board) {
        Topic savedTopic = getTopic(topic.getId());
        savedTopic.setBoard(board);
    }

    @Override
    public void updateTopicTitle(Topic actualTopic, String topicTitle) {
        Topic savedTopic = getTopic(actualTopic.getId());
        savedTopic.setTitle(topicTitle);
    }

    @Override
    public void createTopic(Topic topic) {
        try {
            entityManager.persist(topic);
        } catch (PersistenceException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void mergeTopic(Topic topic) {
        Topic topic1 = getTopic(topic.getId());
        topic1.setContent(null);
        entityManager.remove(topic1);
    }

    public Topic getTopic(int id) {
        return entityManager.find(Topic.class, id);
    }
}
