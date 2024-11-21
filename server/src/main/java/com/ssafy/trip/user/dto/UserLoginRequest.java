package com.ssafy.trip.user.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserLoginRequest {

    private String username;
    private String password;
}