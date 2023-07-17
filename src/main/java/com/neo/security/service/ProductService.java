package com.neo.security.service;

import java.util.List;

import javax.validation.Valid;

import com.neo.security.entity.Product;

public interface ProductService {

	List<Product> getProducts();

	Product saveProduct(@Valid Product product);

}
