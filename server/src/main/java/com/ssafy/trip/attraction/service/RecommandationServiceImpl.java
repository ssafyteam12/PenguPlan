package com.ssafy.trip.attraction.service;

import com.ssafy.trip.attraction.dto.AttractionRecommendationResponse;
import com.ssafy.trip.attraction.repository.RecommandationRepository;
import com.ssafy.trip.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RecommandationServiceImpl implements RecommandationService {

    private final RecommandationRepository recommandationRepository;
    private final UserRepository userRepository;

    @Override
    public AttractionRecommendationResponse getRecommandations() {
        Long userId = getCurrentUserId();

        return null;
    }

    private Long getCurrentUserId() {
        return Long.parseLong(SecurityContextHolder.getContext().getAuthentication().getName());
//        return 1L;
    }



}
