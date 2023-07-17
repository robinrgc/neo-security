package com.neo.security.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.neo.security.entity.Product;
import com.neo.security.service.ProductService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping("api/v1/products")
@RestController
public class ProductController {

	private final ProductService productService;
	
	@GetMapping
	public List<Product> getAllProducts(){
		return productService.getProducts();
	}
	
	@PostMapping
	public ResponseEntity<Product> addProduct(@RequestBody @Valid Product product){
		Product savedProduct = productService.saveProduct(product);
		return ResponseEntity.status(HttpStatus.CREATED).body(savedProduct);
	}
}
