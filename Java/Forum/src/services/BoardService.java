package services;

/**
 * Created by marc on 01/04/2014.
 */

import dao.BoardDao;
import entities.Board;
import entities.Category;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.List;

@Stateless
public class BoardService {

    @EJB
    private BoardDao boardDao;

    public Board getBoardFromTitle(String title) {
        return boardDao.getBoardFromTitle(title);
    }

    public List<Board> getCategoryBoards(Category category) {
        return boardDao.getCategoryBoard(category);
    }

    public List<Board> getAllBoards() {
        return boardDao.getAllBoards();
    }

    public void createBoard(String title, Category category) {
        Board board = new Board();
        board.setTitle(title);
        board.setCategory(category);
        boardDao.createBoard(board);
    }
}
