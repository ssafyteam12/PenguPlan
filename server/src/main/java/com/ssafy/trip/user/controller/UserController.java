package com.ssafy.trip.user.controller;

import com.ssafy.trip.user.dto.*;
import com.ssafy.trip.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    //회원가입
    @PostMapping("/users")
    public ResponseEntity<UserResponse> register(@Valid @RequestBody UserRegisterRequest request) {
        UserResponse response = userService.register(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    //로그인
    @PostMapping("/auth/login")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody UserLoginRequest request) {
        LoginResponse response = userService.login(request);
        return ResponseEntity.ok(response);
    }

    //유저 정보 조회
    @GetMapping("/users/me")
    public ResponseEntity<UserResponse> getUser() {
        UserResponse response = userService.getUserInfo();
        return ResponseEntity.ok(response);
    }

    @PutMapping("/users/me")
    public ResponseEntity<UserResponse> updateUser(
            @Valid @RequestBody UserUpdateRequest request) {
        System.out.println("수정 이메일 = "+request.getEmail()+", "+ request.getPassword());
        UserResponse response = userService.updateUser(request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/users/me")
    public ResponseEntity<Void> deleteUser() {
        userService.deleteUser();
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/users/me/trips")
    public ResponseEntity<UserTripsResponse> getMyTrips() {
        UserTripsResponse response = userService.getMyTrips();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/users/me/likes")
    public ResponseEntity<UserLikesResponse> getMyLikes() {
        UserLikesResponse response = userService.getMyLikes();
        return ResponseEntity.ok(response);
    }
}