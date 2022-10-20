package com.Pharmacia.crud.relation;

import org.hibernate.Session;

import com.Pharmacia.crud.model.Product;
import com.Pharmacia.crud.model.User;

public class ManyToMany {
	public static void executeRelationManyToMany(Session session) {
		Product product1 = new Product("Carry", "Stephen King");
		Product product2 = new Product("Cujo", "Stephen King");

		User user1 = new User("María", "Arozamena", "maria@gmail.com");
		user1.getProducts().add(product1);
		user1.getProducts().add(product2);

		User user2 = new User("Jerôme", "Fournier", "jekouille@gmail.com");
		user2.getProducts().add(product1);

		session.save(user1);
		session.save(user2);
	}
}
