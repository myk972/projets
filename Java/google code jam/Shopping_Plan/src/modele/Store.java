package modele;

import java.util.List;

/**
 * Created by marc on 14/08/2014.
 */
public class Store {

    private Coordinate coord;
    private List<Item> itemsSold;

    public Coordinate getCoord() {
        return coord;
    }

    public void setCoord(Coordinate coord) {
        this.coord = coord;
    }

    public List<Item> getItemsSold() {
        return itemsSold;
    }

    public void setItemsSold(List<Item> itemsSold) {
        this.itemsSold = itemsSold;
    }
}
