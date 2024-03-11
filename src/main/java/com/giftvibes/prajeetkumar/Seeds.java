package com.giftvibes.prajeetkumar;

import com.giftvibes.prajeetkumar.Models.Products;
import com.giftvibes.prajeetkumar.Service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class Seeds implements CommandLineRunner {
    ProductService productService;
    @Override
    public void run(String... args) throws Exception {
        Products products = Products.builder().price("40").prodName("condom").quantity(1000).category("men").build();
        productService.save(products);
        products = Products.builder().price("10").prodName("pencil").quantity(1000).category("kids").build();
        productService.save(products);
        products = Products.builder().price("100").prodName("lip stick").quantity(100).category("women").build();
        productService.save(products);
    }
}
