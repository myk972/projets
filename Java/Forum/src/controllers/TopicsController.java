package controllers;

import entities.Moderator;
import entities.Topic;
import services.TopicService;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import java.util.List;

/**
 * Created by marc on 31/03/2014.
 */
@ManagedBean
public class TopicsController {

    @EJB
    private TopicService topicService;

    @ManagedProperty("#{userBean}")
    private UserBean userBean;

    private String newTopicTitle;

    public String getNewTopicTitle() {
        return newTopicTitle;
    }

    public void setNewTopicTitle(String newTopicTitle) {
        this.newTopicTitle = newTopicTitle;
    }

    public UserBean getUserBean() {
        return userBean;
    }

    public void setUserBean(UserBean userBean) {
        this.userBean = userBean;
    }

    public DataModel<Topic> showTopics() {
        List<Topic> topics = topicService.getBoardTopics(userBean.getActualBoard());
        return new ListDataModel<Topic>(topics);
    }

    public String goToMessages(Topic topic) {
        userBean.setActualTopic(topic);
        return "messages?faces-redirect=true";
    }

    public boolean isAtLeastModerator() {
        return userBean.getUser() instanceof Moderator;
    }

    public String changeTopicLock(Topic topic) {
        topicService.changeTopicLock(topic);
        return "topics?faces-redirect=true";
    }

    public String mergeTopic(Topic topic) {
        userBean.setActualTopic(topic);
        return "mergingPage?faces-redirect=true";
    }

    public String editTopic(Topic topic) {
        userBean.setActualTopic(topic);
        return "editTopic?faces-redirect=true";
    }

    public String createNewTopic() {
        Topic topic = new Topic();
        topic.setTitle(newTopicTitle);
        topic.setBoard(userBean.getActualBoard());
        topicService.createTopic(topic);
        return "topics?faces-redirect=true";
    }
}
