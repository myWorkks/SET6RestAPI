package com.marolix.service;

import java.io.IOException;
import java.util.List;

import com.marolix.dto.ProductDTO;
import com.marolix.entity.Product;
import com.marolix.exception.ProductException;

public interface ProductService {
	public List<Product> findAllByPagination(Integer pgNo, Integer noOfEntitiesperPage) throws Exception;

	public List<Product> findAllBySorting() throws ProductException;

	public List<Product> sortByPrice() throws ProductException;

	public List<Product> sortByName() throws ProductException;

	public String addProduct(ProductDTO dto) throws ProductException;

	public ProductDTO viewProduct(Integer productId) throws ProductException;

	public ProductDTO updateProductPrice(Integer prodId, Float price) throws ProductException;

	public String deleteProduct(Integer Id) throws ProductException;

	public ProductDTO addProductWithPhoto(ProductDTO dto) throws ProductException, IOException;
}
