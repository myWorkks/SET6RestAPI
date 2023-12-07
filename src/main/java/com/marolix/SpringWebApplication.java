package com.marolix;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.marolix.service.ProductServiceImpl;

@SpringBootApplication
public class SpringWebApplication {

	public static void main(String[] args) throws Exception {
	ConfigurableApplicationContext con=	SpringApplication.run(SpringWebApplication.class, args);
//
//		ProductServiceImpl productServiceImpl = con.getBean(ProductServiceImpl.class);
//		productServiceImpl.updateProductPrice(1, 50000.0f);
	}

}
