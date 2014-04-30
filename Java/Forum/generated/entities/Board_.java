package entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Board.class)
public abstract class Board_ {

	public static volatile SingularAttribute<Board, Integer> id;
	public static volatile SingularAttribute<Board, Category> category;
	public static volatile ListAttribute<Board, Topic> topics;
	public static volatile SingularAttribute<Board, String> title;

}

