package com.Pharmacia.crud.service;

import java.util.List;

import com.Pharmacia.crud.model.Product;

public interface ProductService {
	void save(Product product);

	List<?> listProducts();

	void deleteProduct(long id);

	Product getProduct(long id);

	long numProducts();
}
