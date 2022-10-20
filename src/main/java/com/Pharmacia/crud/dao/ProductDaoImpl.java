package com.Pharmacia.crud.dao;

import java.util.Date;
import java.util.List;

import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.Pharmacia.crud.other.Fecha;
import com.Pharmacia.crud.model.Product;
import com.Pharmacia.crud.model.Category;

@Repository
public class ProductDaoImpl implements ProductDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void save(Product product) {
		Category category = null;
		category = (Category) sessionFactory.getCurrentSession()
				.createQuery("from Category c where c.category_name = :categoria_enviada")
				.setParameter("categoria_enviada", product.getCategory().getCategory_name()).uniqueResult();
		if (category != null) {
			System.out.println("category name cargada:" + product.getCategory().getCategory_name() + ". Fecha" + new Date());
			product.setCategory(category);
			category.getProducts().add(product);
			if(product.getId() > 0) {
				System.out.println("Actu de libro");
				product.setUpdatedAt(Fecha.getTimeStamp("Europe/Madrid"));		
				sessionFactory.getCurrentSession().merge(category);
			} else {
				System.out.println("Creación de libro");
				product.setUpdatedAt(Fecha.getTimeStamp("Europe/Madrid"));
				product.setCreatedAt(Fecha.getTimeStamp("Europe/Madrid"));	
				sessionFactory.getCurrentSession().persist(category);
			}
			
		}

		//sessionFactory.getCurrentSession().flush();
	}

	@Override
	public List<?> list() {
		String sentencia;
		TypedQuery<?> query;
		sentencia = "from Product p ORDER BY p.title";
		System.out.println(sentencia);
		query = sessionFactory.getCurrentSession().createQuery(sentencia);
		return query.getResultList();
	}

	@Override
	public void delete(long id) {
		Product savedProductEntity = sessionFactory.getCurrentSession().find(Product.class, id);
		sessionFactory.getCurrentSession().remove(savedProductEntity);

	}

	@Override
	public Product get(long id) {
		Session session = sessionFactory.getCurrentSession();
		Product product = session.get(Product.class, id);
		return product;
	}

	@Override
	public long num() {
		long count = ((long) sessionFactory.getCurrentSession().createQuery("SELECT COUNT(*) FROM Product")
				.uniqueResult());
		return count;
	}

}
