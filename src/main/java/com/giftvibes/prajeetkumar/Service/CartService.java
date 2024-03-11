package com.giftvibes.prajeetkumar.Service;

import com.giftvibes.prajeetkumar.Models.Products;
import com.giftvibes.prajeetkumar.Models.User;
import com.giftvibes.prajeetkumar.Repository.ProductsRepository;
import com.giftvibes.prajeetkumar.Repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CartService {
    UserRepository userRepository;
    ProductService productService;

    public void clear(String cartId){
    }

    public User update(String userId, Products products) throws Exception {

        User user = userRepository.findById(userId).get();
        products = productService.findByName(products.getProdName());
        user.getCartItems().add(products);
        return userRepository.save(user);
    }
    public User remove(String userId, Products products) throws Exception {
        User user = userRepository.findById(userId).get();
        user.getCartItems().remove(products);
        return userRepository.save(user);
    }

    public List<Products> getCart(String userId){
        User user = userRepository.findById(userId).orElseThrow();
        return userRepository.findById(userId).orElseThrow().getCartItems();
    }
}
