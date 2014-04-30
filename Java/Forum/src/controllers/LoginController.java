package controllers;

import com.sun.istack.internal.NotNull;
import entities.User;
import org.hibernate.validator.constraints.NotEmpty;
import services.LoginService;
import services.MessageService;
import services.UserService;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

/**
 * Created by marc on 29/03/2014.
 */
@ManagedBean
public class LoginController {
    @EJB
    private LoginService loginService;
    @EJB
    private MessageService messageService;
    @EJB
    private UserService userService;
    @NotEmpty
    @NotNull
    private String username;
    @NotEmpty
    @NotNull
    private String password;
    @ManagedProperty("#{userBean}")
    private UserBean userBean;
    private String nbMessages;
    private String nbUsers;

    public LoginController() {
    }


    @PostConstruct
    public void init() {
        nbMessages = messageService.getNbMessages();
        nbUsers = userService.getNbUsers();
    }

    public LoginService getLoginService() {
        return loginService;
    }

    public void setLoginService(LoginService loginService) {
        this.loginService = loginService;
    }

    public UserBean getUserBean() {
        return userBean;
    }

    public void setUserBean(UserBean userBean) {
        this.userBean = userBean;
    }

    public String login() {
        User user = loginService.authenticateUser(username, password);
        userBean.setUser(user);
        return (user != null) ? "categories?faces-redirect=true" : null;
    }

    public String goToForumAsAnonymous() {
        userBean.setUser(null);
        return "categories.xhtml";
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setNbMessages(String nbMessages) {
        this.nbMessages = nbMessages;
    }

    public String getNbMessages() {
        return nbMessages;
    }

    public void setNbUsers(String nbUsers) {
        this.nbUsers = nbUsers;
    }

    public String getNbUsers() {
        return nbUsers;
    }
}
