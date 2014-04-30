package entities;

/**
 * Created by marc on 03/04/2014.
 */
public enum Roles {

    ADMINISTRATOR ("administrator"),
    MODERATOR ("moderator"),
    USER ("user");

    private String role;

    Roles(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return role;
    }
}
