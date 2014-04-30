package controllers;

import entities.Roles;
import entities.User;
import org.hibernate.validator.constraints.NotEmpty;
import services.UserService;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.validation.constraints.Pattern;
import java.util.Date;

/**
 * Created by marc on 04/04/2014.
 */
@ManagedBean
public class RegisterController {


    @EJB
    private UserService userService;
    @NotEmpty(message = "username cannot be empty")
    private String username;
    @NotEmpty(message = "password cannot be empty")
    private String password;
    @NotEmpty(message = "please confirm password")
    private String confirmation;
    @NotEmpty(message = "email cannot be empty")
    @Pattern(regexp = "[\\w_\\.-]+@[\\w_-]+\\.\\w+", message = "must be a valid email address")
    private String email;


    public String register() {
        boolean success = false;
        User user = userService.getUser(username);
        if(user == null && password.equals(confirmation)) {
            user = new User();
            user.setRole(Roles.USER.toString());
            user.setUsername(username);
            user.setEmail(email);
            user.setPassword(password);
            user.setCreationDate(new Date());
            success = userService.createUser(user);
        }
        return success ? "home?faces-redirect=true" : "register";
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

    public String getConfirmation() {
        return confirmation;
    }

    public void setConfirmation(String confirmation) {
        this.confirmation = confirmation;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
