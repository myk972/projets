package dao;

import entities.User;

import javax.ejb.Local;
import java.util.List;

/**
 * Created by marc on 29/03/2014.
 */
@Local
public interface UserDao {

    public User getUser(String username);

    public boolean createUser(User user);

    public List<User> getAllUsers();
}
