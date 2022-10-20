package com.Pharmacia.crud.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.TypedQuery;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
/*he cambiado la clase importada*/
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.Pharmacia.crud.model.Product;
import com.Pharmacia.crud.model.User;
import com.Pharmacia.crud.other.Fecha;

@Repository
public class UserDaoImpl implements UserDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void save(User user) {
		sessionFactory.getCurrentSession().saveOrUpdate(user);
	}

	@Override
	public List<User> list() {
		@SuppressWarnings("unchecked")
		TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("from User");
		return query.getResultList();
	}

	@Override
	public void delete(String username) {
		Query<?> query = sessionFactory.getCurrentSession().createQuery("delete from User where username =:username");
		query.setParameter("username", username);
		query.executeUpdate();
	}

	@Override
	public User get(String username) {
		User user = null;
		Session session = sessionFactory.getCurrentSession();
		user = session.get(User.class, username);
		return user;
	}

	@Override
	public long num() {
		long count = ((long) sessionFactory.getCurrentSession().createQuery("SELECT COUNT(*) FROM User")
				.uniqueResult());
		return count;
	}

	@Override
	public void loanProducts(User user) {
		// Qué lista tenemos ?
		// System.out.println(user.getBooks());
		// Metemos la lista en una provisional
		List<Product> listaEnviada = user.getProducts();
		// Borramos la lista anterior
		// Eso provoca que se borrará los registros de la BDD
		user.setProducts(new ArrayList<>());
		// Cargamos los libros enviados en una nueva lista
		for (Product product : listaEnviada) {
			// Por cada elemento enviado en el form
			// hacemos una frase que usaremos para la consulta
			String sentencia = "from Product product where product.title = :title";
			// Cargamos la session factory y a base de la frase, hacemos una consulta
			// para sacar un único libro
			Product loanedProductEntity = (Product) sessionFactory.getCurrentSession().createQuery(sentencia)
					.setParameter("title", product.getTitle()).uniqueResult();
			// Añadimos el libro a la lista de libros que tiene nuestro usuario
			user.getProducts().add(loanedProductEntity);
		}
		// Grabamos
		user.setUpdatedAt(Fecha.getTimeStamp("Europe/Madrid"));
		sessionFactory.getCurrentSession().merge(user);
		// Sincronizamos la BDD
		sessionFactory.getCurrentSession().flush();

	}

//	@Override
//	public boolean confirmExisting(String email) {
//		String sentencia = "from User user where user.email = :email";
//		Session session = sessionFactory.getCurrentSession();
//		
//		return (boolean) session.createQuery(sentencia).uniqueResult();
//	}

}
