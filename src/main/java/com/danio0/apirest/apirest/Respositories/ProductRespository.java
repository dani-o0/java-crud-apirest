package com.danio0.apirest.apirest.Respositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.danio0.apirest.apirest.Entities.Product;

public interface ProductRespository extends JpaRepository<Product, Long>
{
    
}
