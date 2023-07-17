package com.neo.security.service;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import com.neo.security.entity.Product;
import com.neo.security.repository.ProductRepository;

import lombok.RequiredArgsConstructor;

//@RequiredArgsConstructor
@Service
public class ProductServiceImpl implements ProductService{

	@Autowired
	private ProductRepository productRepository;

	@Override
	public List<Product> getProducts() {
		return productRepository.findAll();
	}

	@Override
	public Product saveProduct(@Valid Product product) {
		return productRepository.save(product);
	}
	
	
	
}
