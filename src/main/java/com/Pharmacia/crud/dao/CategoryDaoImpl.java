package com.Pharmacia.crud.dao;

import java.util.List;

import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.Pharmacia.crud.model.Category;
import com.Pharmacia.crud.other.Fecha;

@Repository
public class CategoryDaoImpl implements CategoryDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public void save(Category category) {
		if (category.getId() > 0) {
			category.setUpdatedAt(Fecha.getTimeStamp("Europe/Madrid"));
			sessionFactory.getCurrentSession().saveOrUpdate(category);
		} else {
			category.setUpdatedAt(Fecha.getTimeStamp("Europe/Madrid"));
			category.setCreatedAt(Fecha.getTimeStamp("Europe/Madrid"));
			sessionFactory.getCurrentSession().save(category);
		}
		
	

	}

	@Override
	public List<?> list() {
		String sentencia = "from Category c ORDER BY c.category_name ";
		System.out.println(sentencia);	
		TypedQuery<?> query = sessionFactory.getCurrentSession().createQuery(sentencia);
		query = sessionFactory.getCurrentSession().createQuery(sentencia);
		return query.getResultList();
	}

	@Override
	public void delete(long id) {
		Category savedCategoryEntity = sessionFactory.getCurrentSession().find(Category.class, id);
		sessionFactory.getCurrentSession().remove(savedCategoryEntity);
	}

	@Override
	public Category get(long id) {
		Session session = sessionFactory.getCurrentSession();
		Category category = session.get(Category.class, id);
		return category;
	}

	@Override
	public long num() {
		long count = ((long) sessionFactory.getCurrentSession().createQuery("SELECT COUNT(*) FROM Category")
				.uniqueResult());
		return count;
	}

}
