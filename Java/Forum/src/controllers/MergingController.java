package controllers;

import entities.Topic;
import services.TopicService;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import java.util.List;

/**
 * Created by marc on 03/04/2014.
 */
@ManagedBean
public class MergingController {

    @ManagedProperty("#{userBean}")
    private UserBean userBean;
    @EJB
    private TopicService topicService;
    private Topic selectedTopic;
    private List<Topic> topics;

    public UserBean getUserBean() {
        return userBean;
    }

    public void setUserBean(UserBean userBean) {
        this.userBean = userBean;
    }

    public Topic getSelectedTopic() {
        return selectedTopic;
    }

    public void setSelectedTopic(Topic selectedTopic) {
        this.selectedTopic = selectedTopic;
    }

    public List<Topic> getTopics() {
        return topics;
    }

    public void setTopics(List<Topic> topics) {
        this.topics = topics;
    }

    @PostConstruct
    public void init() {
        topics = topicService.getAllTopics();
        selectedTopic = topics.get(0);
    }

    public String mergeTopic() {
        topicService.mergeTopic(userBean.getActualTopic(), selectedTopic);
        return "topics?faces-redirect=true";
    }
}
