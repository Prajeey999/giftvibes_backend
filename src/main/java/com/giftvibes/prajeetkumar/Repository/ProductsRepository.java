package com.giftvibes.prajeetkumar.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.giftvibes.prajeetkumar.Models.Products;

import java.util.List;


public interface ProductsRepository extends JpaRepository<Products, String> {
      List<Products> findByProdId(String prodId);
}
