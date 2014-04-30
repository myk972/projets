package entities;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.util.List;

/**
 * Created by marc on 26/02/2014.
 */
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @NotEmpty
    @Column (nullable = false)
    private String firstName;

    @NotEmpty
    @Column (nullable = false)
    private String lastName;

    @NotEmpty
    @Column (nullable = false, unique = true)
    private String email;

    @OneToMany
    private List<Quizz> passedQuizzes;
}
