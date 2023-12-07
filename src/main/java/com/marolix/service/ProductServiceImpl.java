package com.marolix.service;

import java.io.IOException;
import java.lang.StackWalker.Option;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.marolix.dto.ProductDTO;
import com.marolix.entity.Product;
import com.marolix.exception.ProductException;
import com.marolix.repository.ProductRepository;

@Service(value = "productService")
@Transactional
public class ProductServiceImpl implements ProductService {
	@Autowired
	private ProductRepository productRepository;

	@Override

	public List<Product> findAllByPagination(Integer pgNo, Integer noOfEntitiesperPage) throws ProductException {

		Pageable pagable = PageRequest.of(pgNo, noOfEntitiesperPage);
		Page<Product> page = productRepository.findAll(pagable);

		// hasNext()
		// next()
		List<Product> products = new ArrayList<Product>();
		if (page.hasContent()) {

			products = page.getContent();

		}
		if (products.isEmpty())
			throw new ProductException("no products found");
		return products;
	}

	@Override
	public List<Product> findAllBySorting() throws ProductException {

		Sort s = Sort.by("prodId").descending();
		Iterable<Product> itr = productRepository.findAll(s);
		Iterator<Product> itr1 = itr.iterator();
		List<Product> products = new ArrayList<Product>();
		while (itr1.hasNext()) {
			products.add(itr1.next());
		}
		if (products.isEmpty())
			throw new ProductException("no products found");
		return products;
	}

	@Override
	public List<Product> sortByPrice() throws ProductException {
		Pageable pg = PageRequest.of(0, 1);
		List<Product> list = productRepository.findAllByOrderByPriceDesc(pg);
		if (list.isEmpty())
			throw new ProductException("no products found");
		return list;
	}

	@Override
	public List<Product> sortByName() throws ProductException {
		List<Product> list = productRepository.sortByName();
		if (list.isEmpty())
			throw new ProductException("no products found");
		return list;
	}

	@Override
	public String addProduct(ProductDTO dto) throws ProductException {
		Product prod = productRepository.findByProdName(dto.getProdName());
		if (prod != null)
			throw new ProductException("Product with name " + dto.getProdName() + " is already available");

		Product p = new Product();
		p.setDescription(dto.getDescription());
		p.setProdName(dto.getProdName());
		p.setPrice(dto.getPrice());
		p = productRepository.save(p);
		return "Product added successfuly with product id " + p.getProdId();
	}

	@Override
	public ProductDTO viewProduct(Integer productId) throws ProductException {
		Optional<Product> opt = productRepository.findById(productId);
		Product p = opt.orElseThrow(() -> new ProductException("no product found with id " + productId));

		ProductDTO dto = new ProductDTO();
		dto.setProdId(p.getProdId());
		dto.setPrice(p.getPrice());
		dto.setDescription(p.getDescription());
		dto.setProdName(p.getProdName());
		return dto;
	}

	@Override
	public ProductDTO updateProductPrice(Integer prodId, Float price) throws ProductException {
		System.out.println("it is service");
		Optional<Product> optional = productRepository.findById(prodId);
		System.out.println("after invoking repo");
		Product product = optional.orElseThrow(() -> new ProductException("no product found with id " + prodId));
		product.setPrice(price);
		System.out.println(product);
		product = productRepository.save(product);

		ProductDTO dto = new ProductDTO();
		dto.setDescription(product.getDescription());
		dto.setPrice(product.getPrice());
		dto.setProdId(prodId);
		dto.setProdName(product.getProdName());
		return dto;
	}

	@Override
	public String deleteProduct(Integer Id) throws ProductException {
		System.out.println("it is service");
		Optional<Product> optional = productRepository.findById(Id);
		System.out.println("after invoking repo");
		Product product = optional.orElseThrow(() -> new ProductException("no product found with id " + Id));
		productRepository.delete(product);
		return "deleted product successfully with id " + Id;
	}

	@Override
	public ProductDTO addProductWithPhoto(ProductDTO dto) throws ProductException, IOException {
		System.out.println("in service");
		Product prod = productRepository.findByProdName(dto.getProdName());
		if (prod != null)
			throw new ProductException("Product with name " + dto.getProdName() + " is already available");
		

		Product p = new Product();
		p.setDescription(dto.getDescription());
		p.setProdName(dto.getProdName());
		p.setPrice(dto.getPrice());

		MultipartFile file = dto.getFile();
		byte[] bytes = file.getBytes();
		p.setFile(bytes);

		String name = file.getOriginalFilename();
		p.setFilapath(name);
		p = productRepository.save(p);
		dto = new ProductDTO();
		dto.setDescription(p.getDescription());
		dto.setFilePath(p.getFilapath());
		dto.setPrice(p.getPrice());
		dto.setProdName(p.getProdName());
		dto.setProdId(p.getProdId());
		return dto;

	}

}
