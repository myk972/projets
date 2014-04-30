package entities;

import javax.persistence.*;

/**
 * Created by marc on 26/02/2014.
 */
@Entity
public class Quizz {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Embedded
    private Question firstQuestion;

    @Embedded
    private Question secondQuestion;

    @Embedded
    private Question thirdQuestion;

    @Embedded
    private Question forthQuestion;

    @Embedded
    private Question fifthQuestion;
}
