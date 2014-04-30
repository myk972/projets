package dao.jpa;

import dao.MessageDao;
import entities.Message;
import entities.Message_;
import entities.Topic;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * Created by marc on 03/04/2014.
 */
@Stateless
public class JpaMessageDao implements MessageDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Message> getTopicMessages(Topic topic) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Message> query = criteriaBuilder.createQuery(Message.class);
        Root<Message> root = query.from(Message.class);
        query.where(criteriaBuilder.equal(root.get(Message_.topic), topic));
        return entityManager.createQuery(query).getResultList();
    }

    @Override
    public boolean saveMessage(Message message) {
        try {
            entityManager.persist(message);
            return true;
        } catch (PersistenceException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public void mergeTopic(Topic topic, Topic destinationTopic) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Message> query = criteriaBuilder.createQuery(Message.class);
        Root<Message> root = query.from(Message.class);
        query.where(criteriaBuilder.equal(root.get(Message_.topic), topic));
        List<Message> messages = entityManager.createQuery(query).getResultList();
        for (Message message : messages) {
            message.setTopic(destinationTopic);
        }
    }

    @Override
    public void deleteMessage(Message message) {
        Message savedMessage = entityManager.find(Message.class, message.getId());
        entityManager.remove(savedMessage);
    }

    @Override
    public List<Message> getAllMessages() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Message> query = criteriaBuilder.createQuery(Message.class);
        query.from(Message.class);
        return entityManager.createQuery(query).getResultList();
    }
}
