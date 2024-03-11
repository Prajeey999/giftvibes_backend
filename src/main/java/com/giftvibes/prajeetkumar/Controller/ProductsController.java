package com.giftvibes.prajeetkumar.Controller;

import com.giftvibes.prajeetkumar.Service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.giftvibes.prajeetkumar.Models.Products;
import com.giftvibes.prajeetkumar.Repository.ProductsRepository;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RestController
@Tag(name="ProductsController")
@AllArgsConstructor
public class ProductsController {

    ProductService productService;

    @GetMapping("/products")
    public List<Products> getProducts(){
        return productService.getProducts();
    }

    @PostMapping("/products")
    public void addProducts(@RequestBody Products products) {
        productService.save(products) ;
    }

    @DeleteMapping("/Products/delete/{id}")
    public void delProducts(@PathVariable String id){
        productService.deleteById(id);
    }

}
