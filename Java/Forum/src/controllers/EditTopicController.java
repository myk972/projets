package controllers;

import entities.Board;
import services.BoardService;
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
public class EditTopicController {

    @EJB
    private BoardService boardService;

    @EJB
    private TopicService topicService;

    @ManagedProperty("#{userBean}")
    private UserBean userBean;

    private Board selectedBoard;
    private List<Board> boards;
    private String topicTitle;

    public String getTopicTitle() {
        return topicTitle;
    }

    public void setTopicTitle(String topicTitle) {
        this.topicTitle = topicTitle;
    }

    @PostConstruct
    public void init() {
        boards = boardService.getAllBoards();
        selectedBoard = boards.get(0);
        topicTitle = userBean.getActualTopic().getTitle();
    }

    public String editTopic() {
        if (!topicTitle.equals(userBean.getActualTopic().getTitle()))
            topicService.updateTopicTitle(userBean.getActualTopic(), topicTitle);
        if (!selectedBoard.equals(userBean.getActualTopic().getBoard()))
            topicService.moveTopic(userBean.getActualTopic(), selectedBoard);
        return "topics?faces-redirect=true";
    }

    public UserBean getUserBean() {
        return userBean;
    }

    public void setUserBean(UserBean userBean) {
        this.userBean = userBean;
    }

    public Board getSelectedBoard() {
        return selectedBoard;
    }

    public void setSelectedBoard(Board selectedBoard) {
        this.selectedBoard = selectedBoard;
    }

    public List<Board> getBoards() {
        return boards;
    }

    public void setBoards(List<Board> boards) {
        this.boards = boards;
    }
}
