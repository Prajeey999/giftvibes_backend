package com.giftvibes.prajeetkumar.Service;

import com.giftvibes.prajeetkumar.Models.Products;
import com.giftvibes.prajeetkumar.Repository.ProductsRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductService {
    ProductsRepository productsRepository;

    public List<Products> getProducts(){
        return productsRepository.findAll();
    }

    public Products save(Products products) {
        return productsRepository.save(products);
    }
}
