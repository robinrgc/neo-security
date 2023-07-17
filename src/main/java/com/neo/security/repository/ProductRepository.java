package com.neo.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.neo.security.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Integer>{

}
