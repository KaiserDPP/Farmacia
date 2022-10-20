package com.Pharmacia.crud.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.Constraint;
import javax.validation.Valid;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "product")	
public class Product extends DateColumns{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long id;

	@Column(name = "title")
	@Size(max = 225, min = 1, message = "{product.title.invalid}")
	@NotBlank(message = "{product.value.required}")
	private String title;

	@Column(name = "code")
	@Size(max = 225, min = 1, message = "{product.code.invalid}")
	@NotBlank(message = "{product.value.required}")
	private String code;

	//https://stackoverflow.com/questions/2990799/difference-between-fetchtype-lazy-and-eager-in-java-persistence-api
	//de uno a uno se puede permitir el uso de ALL
	@OneToOne(cascade = CascadeType.ALL, 
			mappedBy = "product", fetch = FetchType.LAZY)
	@Valid
	private ProductDetails productDetails;

	public ProductDetails getProductDetails() {
		return productDetails;
	}

	public void setProductDetails(ProductDetails productDetails) {
		this.productDetails = productDetails;
	}

	
	@ManyToOne
	@JoinColumn(name = "cat_id")
	@Valid
	private Category category;

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	@ManyToMany(fetch = FetchType.EAGER, mappedBy = "products")
	private List<User> users = new ArrayList<>();

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	
	public Product (long id, String title, String code) {	
		this.id = id; 
		this.title = title;
		this.code = code;
	}
	
	public Product (String title, String code) {		
		this.title = title;
		this.code = code;
	}
	
	public Product (String title) {		
		this.title = title;
	
	}

	// MÁS GETTERS / SETTERS

	public Product() {
		// TODO Auto-generated constructor stub
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Override
	public String toString() {
		return "Libro ID: " + getId() 
		+ "\nTítulo: " + getTitle() 
		+ "\nAutor:" + getCode() 
		+ "\n" + getProductDetails();
	}
	
	

}
