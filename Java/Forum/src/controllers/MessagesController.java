package controllers;

import entities.Message;
import entities.Roles;
import services.MessageService;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import java.util.List;

/**
 * Created by marc on 03/04/2014.
 */
@ManagedBean
public class MessagesController {

    @EJB
    private MessageService messageService;

    @ManagedProperty("#{userBean}")
    private UserBean userBean;

    private String messageContent;

    public DataModel<Message> showMessages() {
        List<Message> messages = messageService.getTopicMessages(userBean.getActualTopic());
        return new ListDataModel<Message>(messages);
    }

    public boolean canWrite() {
        if (userBean.getUser() == null) {
            return false;
        } else {
            String userRole = userBean.getUser().getRole();
            boolean topicLocked = userBean.getActualTopic().isLocked();
            return !(topicLocked && (userRole.equals(Roles.USER.toString())));
        }
    }

    public String saveMessage() {
        Message message = new Message();
        message.setContent(messageContent);
        message.setCreator(userBean.getUser());
        message.setTopic(userBean.getActualTopic());
        boolean saveSuccess = messageService.saveMessage(message);
        return (saveSuccess) ? "messages?faces-redirect=true" : null;
    }

    public String deleteMessage(Message message) {
        messageService.deleteMessage(message);
        return "messages?faces-redirect=true";
    }

    public String getMessageContent() {
        return messageContent;
    }

    public void setMessageContent(String messageContent) {
        this.messageContent = messageContent;
    }


    public UserBean getUserBean() {
        return userBean;
    }

    public void setUserBean(UserBean userBean) {
        this.userBean = userBean;
    }
}
