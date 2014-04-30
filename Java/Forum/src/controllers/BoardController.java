package controllers;

import entities.Board;
import services.BoardService;

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
public class BoardController {

    @ManagedProperty("#{userBean}")
    UserBean userBean;

    @EJB
    private BoardService boardService;

    private String newBoardTitle;


    public UserBean getUserBean() {
        return userBean;
    }

    public void setUserBean(UserBean userBean) {
        this.userBean = userBean;
    }

    public DataModel<Board> showBoards() {
        List<Board> boards = boardService.getCategoryBoards(userBean.getActualCategory());
        return new ListDataModel<Board>(boards);
    }

    public String goToTopics(Board board) {
        userBean.setActualBoard(board);
        return "topics?faces-redirect=true";
    }

    public String createNewBoard() {
        boardService.createBoard(newBoardTitle, userBean.getActualCategory());
        return "boards?faces-redirect=true";
    }

    public void setNewBoardTitle(String newBoardTitle) {
        this.newBoardTitle = newBoardTitle;
    }

    public String getNewBoardTitle() {
        return newBoardTitle;
    }
}
