package com.giftvibes.prajeetkumar.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.giftvibes.prajeetkumar.Models.Order;

import java.util.List;


public interface OrderRepository extends JpaRepository<Order, String> {
      List<Order> findByOrdId(String ordId);
}
