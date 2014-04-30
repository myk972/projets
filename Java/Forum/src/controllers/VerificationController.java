package controllers;

import entities.Administrator;
import entities.Moderator;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

/**
 * Created by marc on 04/04/2014.
 */
@ManagedBean
public class VerificationController {

    @ManagedProperty("#{userBean}")
    private UserBean userBean;

    public UserBean getUserBean() {
        return userBean;
    }

    public void setUserBean(UserBean userBean) {
        this.userBean = userBean;
    }

    public boolean userIsAuthenticated() {
        return userBean.getUser() != null;
    }

    public boolean userIsAtLeastModerator() {
        return userBean.getUser() instanceof Moderator;
    }

    public boolean userIsAdministrator() {
        return userBean.getUser() instanceof Administrator;
    }
}
