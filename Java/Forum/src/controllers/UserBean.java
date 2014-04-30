package controllers;

import entities.Board;
import entities.Category;
import entities.Topic;
import entities.User;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 * Created by marc on 29/03/2014.
 */
@ManagedBean
@SessionScoped
public class UserBean {

    private User user;
    private Category actualCategory;
    private Topic actualTopic;
    private Board actualBoard;

    public Category getActualCategory() {
        return actualCategory;
    }

    public void setActualCategory(Category actualCategory) {
        this.actualCategory = actualCategory;
    }

    public Topic getActualTopic() {
        return actualTopic;
    }

    public void setActualTopic(Topic actualTopic) {
        this.actualTopic = actualTopic;
    }

    public Board getActualBoard() {
        return actualBoard;
    }

    public void setActualBoard(Board actualBoard) {
        this.actualBoard = actualBoard;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
