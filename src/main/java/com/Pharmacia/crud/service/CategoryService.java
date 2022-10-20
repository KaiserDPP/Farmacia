package com.Pharmacia.crud.service;

import java.util.List;

import com.Pharmacia.crud.model.Product;
import  com.Pharmacia.crud.model.Category;

public interface CategoryService {
	void save(Category category);

	List<?> listCategories();

	void deleteCategory(long id);

	Category getCategory(long id);

	long numCategories();
}
