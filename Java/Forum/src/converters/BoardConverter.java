package converters;


import entities.Board;
import services.BoardService;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

/**
 * Created by marc on 01/04/2014.
 */
@ManagedBean
public class BoardConverter implements Converter{

    @EJB
    private BoardService boardService;

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String s) {
        return boardService.getBoardFromTitle(s);
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object o) {
        Board board = (Board) o;
        return board.getTitle();
    }
}
