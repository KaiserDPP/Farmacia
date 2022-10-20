package com.Pharmacia.crud.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;

import org.springframework.validation.annotation.Validated;

@Entity
@Table(name = "product_details")
@Validated
public class ProductDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long id;

	@Column(name = "issue_year")
	@Digits(integer=4, fraction=0)
	@Pattern(regexp = "[0-9]{4}")
	@Min(value = 1500, message = "{product.issue_year_min.invalid}")
	@Max(value = 2022, message = "{product.issue_year_max.invalid}")
	private String issue_year;

	@Column(name = "expire_year")
	@Digits(integer=4, fraction=0)
	@Min(value = 1975, message = "{product.expire_year_min.invalid}")
	@Max(value = 2022, message = "{product.expire_year_max.invalid}")
	private String expire_year;

	@Column(name = "units_number")
	@Digits(integer=4, fraction=0)
	@Min(value = 20, message = "{product.units_number_min.invalid}")
	@Max(value = 2000, message = "{product.units_number_max.invalid}")
	private String units_number;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id")
	private Product product;

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public ProductDetails() {
	}

	public ProductDetails(long id, String issue_year, String expire_year, String units_number) {
		this.id = id;
		this.issue_year = issue_year;
		this.expire_year = expire_year;
		this.units_number = units_number;
	}

	// MÁS GETTERS / SETTERS

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getIssue_year() {
		return issue_year;
	}

	public void setIssue_year(String issue_year) {
		this.issue_year = issue_year;
	}

	public String getExpire_year() {
		return expire_year;
	}

	public void setExpire_year(String expire_year) {
		this.expire_year = expire_year;
	}

	public String getUnits_number() {
		return units_number;
	}

	public void setUnits_number(String units_number) {
		this.units_number = units_number;
	}

	@Override
	public String toString() {
		return "Publicado en " + getIssue_year()
				+ "\nComprado en " + getExpire_year() + ".\n" + getUnits_number() + " páginas";
	}

}
