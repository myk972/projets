package services;

import dao.MessageDao;
import dao.TopicDao;
import entities.Board;
import entities.Topic;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.List;

/**
 * Created by marc on 31/03/2014.
 */
@Stateless
public class TopicService {

    @EJB
    private TopicDao topicDao;

    @EJB
    private MessageDao messageDao;

    public Topic getTopicFromTitle(String title) {
        return topicDao.getTopicFromTitle(title);
    }

    public List<Topic> getBoardTopics(Board board) {
        return topicDao.getBoardTopics(board);
    }

    public void changeTopicLock(Topic topic) {
        topicDao.changeTopicLock(topic);
    }

    public List<Topic> getAllTopics() {
        return topicDao.getAllTopics();
    }

    public void mergeTopic(Topic topic, Topic destinationTopic) {

        messageDao.mergeTopic(topic, destinationTopic);
        topicDao.mergeTopic(topic);
    }

    public void moveTopic(Topic topic, Board board) {
        topicDao.updateTopic(topic, board);
    }

    public void updateTopicTitle(Topic actualTopic, String topicTitle) {
        topicDao.updateTopicTitle(actualTopic, topicTitle);
    }

    public void createTopic(Topic topic) {
        topicDao.createTopic(topic);
    }
}
