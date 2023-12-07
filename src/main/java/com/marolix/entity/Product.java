package com.marolix.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;

@Entity

public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer prodId;
	@Column(unique = true)
	private String prodName;
	@Lob
	private byte[] file;
	private String filapath;

	public byte[] getFile() {
		return file;
	}

	public void setFile(byte[] file) {
		this.file = file;
	}

	public String getFilapath() {
		return filapath;
	}

	public void setFilapath(String filapath) {
		this.filapath = filapath;
	}

	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	@Lob
	private String description;
	private Float price;

	public Integer getProdId() {
		return prodId;
	}

	public void setProdId(Integer prodId) {
		this.prodId = prodId;
	}

	public String getProdName() {
		return prodName;
	}

	public void setProdName(String prodName) {
		this.prodName = prodName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "Product [prodId=" + prodId + ", prodName=" + prodName + ", description=" + description + ", price="
				+ price + "]";
	}

}
