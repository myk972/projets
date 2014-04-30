package dao;

import entities.Board;
import entities.Category;

import javax.ejb.Local;
import java.util.List;

/**
 * Created by marc on 01/04/2014.
 */
@Local
public interface BoardDao {

    public Board getBoardFromTitle(String title);

    public List<Board> getCategoryBoard(Category category);

    public List<Board> getAllBoards();

    public void createBoard(Board board);
}
