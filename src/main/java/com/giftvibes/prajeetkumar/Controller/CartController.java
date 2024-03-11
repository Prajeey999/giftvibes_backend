package com.giftvibes.prajeetkumar.Controller;

import com.giftvibes.prajeetkumar.Models.Order;
import com.giftvibes.prajeetkumar.Models.Products;
import com.giftvibes.prajeetkumar.Models.User;
import com.giftvibes.prajeetkumar.Service.CartService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Tag(name="OrderController")
@AllArgsConstructor
public class CartController {

    CartService cartService;

    @PutMapping("/cart/{userId}")
    public User addToCart(@RequestBody Products products, @PathVariable String userId) throws Exception {
        return cartService.update(userId, products);
    }

    @DeleteMapping("/cart/{userId}")
    public User remove(@RequestBody Products products, @PathVariable String userId) throws Exception {
        return cartService.remove(userId, products);
    }

    @GetMapping("/cart/{userId}")
    public List<Products> getCarts(@PathVariable String userId) throws Exception {
        return cartService.getCart(userId);
    }

}
