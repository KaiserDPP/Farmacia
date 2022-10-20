package com.Pharmacia.crud.dao;

import java.util.List;

import com.Pharmacia.crud.model.Product;
import com.Pharmacia.crud.model.User;

public interface ProductDao {
	void save(Product product);

	List<?> list();
	
	void delete(long id);
	
	Product get(long id);
	
	long num();	
}
