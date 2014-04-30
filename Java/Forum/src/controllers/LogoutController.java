package controllers;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

/**
 * Created by marc on 08/04/2014.
 */
@ManagedBean
public class LogoutController {

    @ManagedProperty("#{userBean}")
    private UserBean userBean;

    public String logout() {
        userBean.setActualTopic(null);
        userBean.setActualCategory(null);
        userBean.setActualBoard(null);
        userBean.setUser(null);
        return "home.xhtml";
    }

    public UserBean getUserBean() {
        return userBean;
    }

    public void setUserBean(UserBean userBean) {
        this.userBean = userBean;
    }
}
