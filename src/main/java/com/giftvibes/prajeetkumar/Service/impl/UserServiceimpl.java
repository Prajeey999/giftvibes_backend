package com.giftvibes.prajeetkumar.Service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.giftvibes.prajeetkumar.Models.User;
import com.giftvibes.prajeetkumar.Repository.UserRepository;
import com.giftvibes.prajeetkumar.Service.UserService;
import com.giftvibes.prajeetkumar.dto.request.RegisterRequest;
import com.giftvibes.prajeetkumar.dto.response.RegisterResponse;
import com.giftvibes.prajeetkumar.dto.response.UserFetchResponse;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceimpl implements UserService {

    private static UserRepository userRepository; 

    @Override
    public RegisterResponse register(RegisterRequest request) {
        throw new UnsupportedOperationException("Unimplemented method 'register'");
    }

    @Override
    public List<UserFetchResponse> getAll(){
        List<User> users = userRepository.findAll();
        return users.stream().map(user -> UserFetchResponse.builder().name(user.getName()).email(user.getEmail()).password(user.getPassword()).build())
                .collect(Collectors.toList());
        // return users.map(user -> UserResponse.builder().name(user.getName()).email(user.getEmail()).build());
    }

    @Override
    public UserFetchResponse getById(String id) throws Exception{
        Optional<User> isUser = userRepository.findById(id);
        
        if(isUser.isEmpty()){
            throw new Exception("User not found with id "+id);
        }
        return UserFetchResponse.builder().name(isUser.get().getName()).email(isUser.get().getEmail()).password(isUser.get().getPassword()).build();
    }
}