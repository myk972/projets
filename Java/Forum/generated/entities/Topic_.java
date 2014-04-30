package entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Topic.class)
public abstract class Topic_ {

	public static volatile ListAttribute<Topic, Message> content;
	public static volatile SingularAttribute<Topic, Integer> id;
	public static volatile SingularAttribute<Topic, String> title;
	public static volatile SingularAttribute<Topic, Board> board;
	public static volatile SingularAttribute<Topic, Boolean> isLocked;

}

