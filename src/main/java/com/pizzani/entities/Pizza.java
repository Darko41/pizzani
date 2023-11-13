package com.pizzani.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "pizza")
public class Pizza {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String ingredients;
	private Integer price;
	private String image;
	@Column(name = "description", length = 512)
	private String description;
	private boolean standOut;
//	private String size;
	
	public Pizza() {
		super();
	}

	public Pizza(Long id, String name, String ingredients,
			Integer price, String image, String description,
			boolean standOut) {
		super();
		this.id = id;
		this.name = name;
		this.ingredients = ingredients;
		this.price = price;
		this.image = image;
		this.description = description;
		this.standOut = standOut;
//		this.size = size;
	}
	
	public Pizza(String name, String ingredients) {
		super();
		this.name = name;
		this.ingredients = ingredients;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIngredients() {
		return ingredients;
	}

	public void setIngredients(String ingredients) {
		this.ingredients = ingredients;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return this.name;
	}

	public boolean isStandOut() {
		return standOut;
	}

	public void setStandOut(boolean standOut) {
		this.standOut = standOut;
	}
//
//	public String getSize() {
//		return size;
//	}
//
//	public void setSize(String size) {
//		this.size = size;
//	}

}
