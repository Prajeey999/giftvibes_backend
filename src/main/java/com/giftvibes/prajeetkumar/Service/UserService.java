package com.giftvibes.prajeetkumar.Service;

import java.util.List;

import com.giftvibes.prajeetkumar.dto.request.RegisterRequest;
import com.giftvibes.prajeetkumar.dto.response.RegisterResponse;
import com.giftvibes.prajeetkumar.dto.response.UserFetchResponse;

public interface UserService {
    RegisterResponse register(RegisterRequest request);

    List<UserFetchResponse> getAll();

    UserFetchResponse getById(String id) throws Exception ;
}