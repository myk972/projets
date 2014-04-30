package entities;

import javax.persistence.*;

/**
 * Created by marc on 28/03/2014.
 */
@Entity
@DiscriminatorValue("moderator")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "role")
public class Moderator extends User{

    public Moderator() {
        this.setRole(Roles.MODERATOR.toString());
    }
}
