package com.marolix.dto;

import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class ProductDTO {

	private Integer prodId;
	@NotNull(message = "plese provide the prodName")
	@Pattern(regexp = "[a-zA-Z]+", message = "provided product name is invalid")
	private String prodName;
	@NotNull(message = "please provide the description")
	private String description;
	private Float price;
	private MultipartFile file;
	private String filePath;

	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

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

	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "ProductDTO [prodId=" + prodId + ", prodName=" + prodName + ", description=" + description + ", price="
				+ price + "]";
	}

}
