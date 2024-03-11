package com.giftvibes.prajeetkumar.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.giftvibes.prajeetkumar.Models.Order;
import com.giftvibes.prajeetkumar.Repository.OrderRepository;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;


@RestController
@RequiredArgsConstructor
@Tag(name="OrderController")


public class OrderController {

    @Autowired
    OrderRepository orderRepository ;

    @PostMapping("/order")
    public void addOrder(@RequestBody Order order) {
        orderRepository.save(order) ;
    }
    
    @GetMapping("/g/ord/{id}")
    public List<Order> getorder(@PathVariable String id) {
        return orderRepository.findByOrdId(id);  
    }

}
