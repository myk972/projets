package modele;

/**
 * Created by marc on 14/08/2014.
 */
public class Item {

    private String name;
    private boolean isPerishable;

    public Item(String name) {
        if (name.endsWith("!")) {
            this.name = name.substring(0, name.length() - 1);
            isPerishable = true;
        } else {
            this.name = name;
            isPerishable = false;
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isPerishable() {
        return isPerishable;
    }

    public void setPerishable(boolean isPerishable) {
        this.isPerishable = isPerishable;
    }
}
