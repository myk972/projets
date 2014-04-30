package dao;

import entities.Message;
import entities.Topic;

import javax.ejb.Local;
import java.util.List;

/**
 * Created by marc on 03/04/2014.
 */
@Local
public interface MessageDao {

    public List<Message> getTopicMessages(Topic topic);

    public boolean saveMessage(Message message);

    public void mergeTopic(Topic topic, Topic destinationTopic);

    public void deleteMessage(Message message);

    public List<Message> getAllMessages();
}
