package services;

import dao.MessageDao;
import entities.Message;
import entities.Topic;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.List;

/**
 * Created by marc on 03/04/2014.
 */
@Stateless
public class MessageService {

    @EJB
    private MessageDao messageDao;

    public List<Message> getTopicMessages(Topic topic) {
        return messageDao.getTopicMessages(topic);
    }

    public boolean saveMessage(Message message) {
        return messageDao.saveMessage(message);
    }

    public void deleteMessage(Message message) {
        messageDao.deleteMessage(message);
    }

    public String getNbMessages() {
        List<Message> messages = messageDao.getAllMessages();
        return String.valueOf(messages.size());
    }
}
