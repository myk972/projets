package services;

import dao.UserDao;
import entities.User;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.List;

/**
 * Created by marc on 04/04/2014.
 */
@Stateless
public class UserService {

    @EJB
    private UserDao userDao;

    public boolean createUser(User user) {
        return userDao.createUser(user);
    }

    public User getUser(String username) {
        return userDao.getUser(username);
    }

    public String getNbUsers() {
        List<User> users = userDao.getAllUsers();
        return String.valueOf(users.size());
    }
}
