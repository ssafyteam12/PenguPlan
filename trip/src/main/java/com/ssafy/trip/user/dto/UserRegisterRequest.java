package com.ssafy.trip.user.dto;

import com.ssafy.trip.user.entity.User;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Builder
public class UserRegisterRequest {
    private String username;
    private String password;
    private String email;

    private UserRegisterRequest(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public static UserRegisterRequest of(User user) {
        return new UserRegisterRequest(user.getUsername(), user.getPassword(), user.getEmail());
    }
}