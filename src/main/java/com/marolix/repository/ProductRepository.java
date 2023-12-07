package com.marolix.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import com.marolix.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {
	List<Product> findAllByOrderByPriceDesc(Pageable pagable);

	@Query(value = "select * from product order by prod_name desc", nativeQuery = true)
	List<Product> sortByName();

	Product findByProdName(String name);
}
