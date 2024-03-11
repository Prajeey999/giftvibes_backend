package com.giftvibes.prajeetkumar.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.giftvibes.prajeetkumar.Models.User;

public interface UserRepository extends JpaRepository<User, String> {
    Optional<User> findByEmail(String email);
}
