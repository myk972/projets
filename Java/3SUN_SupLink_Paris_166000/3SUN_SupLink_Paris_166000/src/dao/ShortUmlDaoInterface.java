package dao;

import entities.Client;
import entities.ShortUml;

public interface ShortUmlDaoInterface {

    void addShortUml(ShortUml uml);

    public void enableUml(ShortUml uml);

    public void disableUml(ShortUml uml);
}