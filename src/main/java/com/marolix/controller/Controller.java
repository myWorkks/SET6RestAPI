package com.marolix.controller;

import java.time.LocalDateTime;
import java.util.Enumeration;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.marolix.dto.ProductDTO;
import com.marolix.entity.Product;
import com.marolix.exception.ErrorInformation;
import com.marolix.exception.ProductException;
import com.marolix.service.ProductService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;

@RestController

@RequestMapping(value = "base-url") // 1
@Validated
public class Controller {
	@Autowired
	private ProductService productService;

	@RequestMapping(value = "api", method = RequestMethod.GET) // 2
	public ResponseEntity<String> apiCall() {
		return new ResponseEntity<String>("hello world", HttpStatus.OK);
	}

	@RequestMapping(value = "api2", method = RequestMethod.GET) // 3
	public ResponseEntity<String> apiCall2() {
		return new ResponseEntity<String>("hello world2", HttpStatus.OK);
	}

	// @RequestMapping(value = "/add-product", method = RequestMethod.POST) // 4
	@PostMapping(value = "/add-product")
	public ResponseEntity<?> addProduct(@Valid @RequestBody ProductDTO dto) throws ProductException {

		return new ResponseEntity<String>(productService.addProduct(dto), HttpStatus.CREATED);

	}

	@PostMapping(value = "/add-product-file")
	public ResponseEntity<ProductDTO> addProductWithPhoto(@ModelAttribute ProductDTO dto) throws Exception {
		System.out.println("in contoller " + dto);

		return new ResponseEntity<ProductDTO>(productService.addProductWithPhoto(dto), HttpStatus.CREATED);
	}

	@GetMapping(value = "/get-products")
	public List<Product> viewAllProducts() throws Exception {
		System.out.println("get method invoked");
		List<Product> dtos = productService.findAllBySorting();
		return dtos;
	}

	@GetMapping(value = "/get-product/prodName/{prodName}")
	public String searchProductByName(
			@Pattern(regexp = "[a-zA-Z]+", message = "please provide a valid name") @PathVariable String prodName) {
		System.out.println("inside get product by name");
		return prodName;
	}

	@GetMapping(value = "get-product")
	public ProductDTO viewProduct(@RequestParam("prodId") Integer prodId) throws Exception {

		return productService.viewProduct(prodId);

	}

	@GetMapping(value = "get-product/{prodId}")
	public ProductDTO viewProduct1(@Valid @Min(value=1,message="product is not in a range") @Max(value=99999,message="productid is not in a range") @PathVariable Integer prodId)
			throws Exception {
		System.out.println("path variabl method invoked");
		return productService.viewProduct(prodId);

	}

	@PutMapping(value = "update-product/prodId/{prodId}/price/{price}")
//http://localhost:8080/base-url/update-product/prodId/1/price/50000.0
	ResponseEntity<ProductDTO> updateProductPrice(@PathVariable Integer prodId, @PathVariable Float price)
			throws Exception {
		System.out.println("it is contrller");
		System.out.println("prodId " + prodId + " price " + price);
		ProductDTO dto = productService.updateProductPrice(prodId, price);
		return new ResponseEntity<ProductDTO>(dto, HttpStatus.OK);
	}

	@DeleteMapping(value = "delete-product")
	ResponseEntity<String> deleteProduct(@RequestParam(value = "product-id") Integer prodId) throws Exception {
		String deleteMessage = productService.deleteProduct(prodId);
		return new ResponseEntity<String>(deleteMessage, HttpStatus.OK);
	}

	@GetMapping(value = "dummy-api")
	public String dummyMethod(@RequestParam("username") String userName, @RequestParam("password") String password) {

		return "Get method :username " + userName + "and password " + password;
	}

	@PostMapping(value = "dummy-api")
	public String dummyMethod1(@RequestParam("username") String userName, @RequestParam("password") String password) {

		return "Post method :username " + userName + "and password " + password;
	}

	public void dummy(MultipartHttpServletRequest request) {

		Enumeration<String> params = request.getParameterNames();
		while (params.hasMoreElements()) {
			String p = params.nextElement();
			System.out.println("param name " + p + " respective value=" + request.getParameter(p));
		}
	}

//	@PostMapping(value = "/add-product")
//	public String addProduct1(@RequestBody ProductDTO dto) throws Exception {
//		System.out.println(dto);
//
//		return productService.addProduct(dto);
//	}
}
//on which server
//local host port:8080
//map api
//http://localhost:8080/api