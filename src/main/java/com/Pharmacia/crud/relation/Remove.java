package com.Pharmacia.crud.relation;

import org.hibernate.Session;

import com.Pharmacia.crud.model.Product;
import com.Pharmacia.crud.model.Category;

public class Remove {
	public static void executeRemoveAction(Session session) {

		//Book savedBookEntity = session.find(Book.class, 15L);
		//session.remove(savedBookEntity);
		//https://stackoverflow.com/questions/3220336/whats-the-use-of-session-flush-in-hibernate/3220403#3220403
		// Flushing is the process of synchronizing the state of the persistence context
		// with the underlying database.
		//session.flush();
		
		//Al no tener la opción REMOVE activada en la relación
		//borrará la categoría pero dejará los libros 
		//Sin embargo los libros seguirán haciendo referencia a 
		//la categoría ausente. Y esto provocará que la página 
		//saque un error500 si se llama la categoría ausente 
		//ya que los datos de categoría no se encuentrarán 
		Category savedCategoryEntity = session.find(Category.class, 7L);		
		session.remove(savedCategoryEntity);
		session.flush();
	}
}
