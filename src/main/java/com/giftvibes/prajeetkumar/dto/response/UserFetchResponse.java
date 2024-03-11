package com.giftvibes.prajeetkumar.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserFetchResponse {
    private String name;
    private String email;
    private String password;
 
}