package dao;

import entities.Board;
import entities.Message;
import entities.Topic;

import javax.ejb.Local;
import java.util.List;

/**
 * Created by marc on 31/03/2014.
 */
@Local
public interface TopicDao {

    public Topic getTopicFromTitle(String title);

    public List<Topic> getBoardTopics(Board board);

    public void changeTopicLock(Topic topic);

    public List<Topic> getAllTopics();

    public void deleteTopic(Topic topic);

    public void addMessages(Topic destinationTopic, List<Message> messages);

    public void updateTopic(Topic topic, Board board);

    public void updateTopicTitle(Topic actualTopic, String topicTitle);

    public void createTopic(Topic topic);

    public void mergeTopic(Topic topic);
}
