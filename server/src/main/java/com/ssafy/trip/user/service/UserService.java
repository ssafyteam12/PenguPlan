package com.ssafy.trip.user.service;

import com.ssafy.trip.attraction.dto.AttractionDTO;
import com.ssafy.trip.attractionLikes.entity.AttractionLikes;
import com.ssafy.trip.attractionLikes.repository.AttractionLikesRepository;
import com.ssafy.trip.attraction.repository.AttractionRepository;
import com.ssafy.trip.tripAttractions.repository.TripAttractionsRepository;
import com.ssafy.trip.trips.entity.Trips;
import com.ssafy.trip.trips.repository.TripsRepository;
import com.ssafy.trip.user.dto.*;
import com.ssafy.trip.user.entity.User;
import com.ssafy.trip.user.exceptions.DuplicateEmailException;
import com.ssafy.trip.user.exceptions.DuplicateUsernameException;
import com.ssafy.trip.user.exceptions.InvalidPasswordException;
import com.ssafy.trip.user.exceptions.UserNotFoundException;
import com.ssafy.trip.user.repository.UserRepository;
import com.ssafy.trip.user.repository.UserTripRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final TripsRepository tripsRepository;
    private final AttractionLikesRepository attractionLikesRepository;
    private final TripAttractionsRepository tripAttractionsRepository;
    private final AttractionRepository attractionRepository;
    private final UserTripRepository userTripRepository;

    @Transactional
    public UserResponse register(UserRegisterRequest request) {

        // Validate unique username and email
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new DuplicateUsernameException("Username already exists");
        }
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new DuplicateEmailException("Email already exists");
        }

        // Create new user
        User user = User.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .email(request.getEmail())
                .build();

        User savedUser = userRepository.save(user);
        return UserResponse.from(savedUser);
    }

    public LoginResponse login(UserLoginRequest request) {
        // Find user and verify password
        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new InvalidPasswordException("Invalid password");
        }

        // Generate JWT token
        String token = jwtTokenProvider.generateToken(user.getUserId().toString());

        return LoginResponse.builder()
                .accessToken(token)
                .tokenType("Bearer")
                .user(UserResponse.from(user))
                .build();
    }

    public UserResponse getUserInfo() {
        Long userId = getCurrentUserId();
        log.info("userId {} login", userId);
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found"));
        return UserResponse.from(user);
    }

    @Transactional
    public UserResponse updateUser(UserUpdateRequest request) {
        Long userId = getCurrentUserId();

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        if (request.getPassword() != null) {
            user.updatePassword(passwordEncoder.encode(request.getPassword()));
        }
        if (request.getEmail() != null) {
            user.updateEmail(request.getEmail());
        }

        return UserResponse.from(user);
    }

    @Transactional
    public void deleteUser() {
        Long userId = getCurrentUserId();

        if (!userRepository.existsById(userId)) {
            throw new UserNotFoundException("User not found");
        }
        userRepository.deleteById(userId);
    }

    public UserTripsResponse getMyTrips() {
        Long userId = getCurrentUserId();

        List<Trips> trips = tripsRepository.findByUserId(userId);
        List<UserTripsDto> userTripsDtos = new ArrayList<>();
        for (int i=0;i<trips.size();i++) {
            List<AttractionDTO> attractions = userTripRepository.findByTripId(trips.get(i).getTripId())
                    .stream()
                    .map(AttractionDTO::from)  // or .map(attraction -> AttractionDTO.from(attraction))
                    .toList();

            UserTripsDto userTripsDto = UserTripsDto.of(trips.get(i), attractions);
            userTripsDtos.add(userTripsDto);
        }
        return UserTripsResponse.from(userTripsDtos);
    }

    public UserLikesResponse getMyLikes() {
        Long userId = getCurrentUserId();
        List<AttractionLikes> likes = attractionLikesRepository.findByUserId(userId);
        return UserLikesResponse.from(likes);
    }

    private Long getCurrentUserId() {

        String temp = SecurityContextHolder.getContext().getAuthentication().getName();
        Long userId = 1L;
        log.info("userId = {}", temp);
        if (!temp.equals("anonymousUser")) userId = Long.parseLong(temp);
        return userId;
    }
}