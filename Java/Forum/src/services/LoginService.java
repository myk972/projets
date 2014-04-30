package services;

import dao.UserDao;
import entities.User;

import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
* Created by marc on 29/03/2014.
*/
@Stateless
public class LoginService {

    @EJB
    private UserDao userDao;

    public User authenticateUser(String username, String password) {
        User user = userDao.getUser(username);
        return (user.getPassword().equals(password)) ? user : null;
    }
}
