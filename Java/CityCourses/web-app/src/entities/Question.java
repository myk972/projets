package entities;

import javax.persistence.Embeddable;

/**
 * Created by marc on 26/02/2014.
 */
@Embeddable
public class Question {

    private String question;
    private String rightAnswer;
    private String wrongAnswer;
}
