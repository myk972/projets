package dao;

import java.util.List;

import entities.Client;
import entities.ShortUml;

public interface ClientDaoInterface {

    void createClient(Client client);
    
    Client trouverClient(String email);

    List<ShortUml> listUml(Client client);
}

