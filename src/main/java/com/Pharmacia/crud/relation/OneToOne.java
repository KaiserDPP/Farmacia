package com.Pharmacia.crud.relation;

import org.hibernate.Session;

import com.Pharmacia.crud.model.Product;
import com.Pharmacia.crud.model.ProductDetails;

public class OneToOne {
	public static void executeRelation1To1(Session session) {
		Product product = new Product();
		product.setTitle("Le tour du monde en 80 jours");
		product.setCode("Jules Vernes");

		ProductDetails productDetails = new ProductDetails();
		productDetails.setUnits_number("290");
		productDetails.setIssue_year("1864");
		productDetails.setExpire_year("1990");

		productDetails.setProduct(product);

		product.setProductDetails(productDetails);
		//guarda solo el objeto book
		//session.save(book);
		//guarda book y sus hijos
		session.persist(product);
	}
}
