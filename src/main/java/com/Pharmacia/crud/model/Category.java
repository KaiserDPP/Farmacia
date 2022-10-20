package com.Pharmacia.crud.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Required;

@Entity
@Table(name = "category")
public class Category extends DateColumns{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long id;

	@Column(name = "category_name")
	@NotBlank(message = "{product.value.required}")
	@NotNull
	@Size(max = 100, min = 1, message = "{product.category_name.invalid}")
	private String category_name;

	// De one to many se autoriza el uso de ALL
	// Pero se debería de evitar a todo coste:
	// No se debe utilizar como comodín ni ponerse en casos de desconocimiento de la
	// funcionalidad de la relación.
	//https://www.nestoralmeida.com/cascade-en-jpa-hibernate/#4-cascadetypeall
	// MERGE es para actualizar datos. Lo hará respectando la relación
	// con los hijos de la clase
	// PERSIST es para crear datos. Lo hará respectando la relación
	// con los hijos de la clase
	// SAVE es automáticamente disponible pero no respeta la relación
	// establecida
	@OneToMany(cascade = { 
			CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REMOVE, 
			CascadeType.DETACH}, mappedBy = "category")
	private List<Product> products = new ArrayList<>();

	public List<Product> getProducts() {
		return products;
	}
	// Los demás son: REMOVE --> borra la fila indicada y sus relaciones
	//asociadas
	// DETACH --> Borra
	// REFRESH --> refresca los datos recargando los contenidos 
	// de la BDD y descartando el objeto gestionado. 
	//Muy especial y muy poco utilizado
	//Útil para garantizar que se esté manejando la versión
	//más actualizada de una entidad en caso de haber sido 
	//modificado por otro usuario

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public Category() {
	}

	public Category(String category_name) {
		this.category_name = category_name;
	}

	// MÁS GETTERS / SETTERS
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCategory_name() {
		return category_name;
	}

	public void setCategory_name(String category_name) {
		this.category_name = category_name;
	}

	@Override
	public String toString() {
		return "Category [getId()=" + getId() + ", getCategory_name()=" + getCategory_name() + "]";
	}

}
