package entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Created by marc on 28/03/2014.
 */
@Entity
@DiscriminatorValue("administrator")
public class Administrator extends Moderator{

    public Administrator() {
        this.setRole(Roles.ADMINISTRATOR.toString());
    }
}
