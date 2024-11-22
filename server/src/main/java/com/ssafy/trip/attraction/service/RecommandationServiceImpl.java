package com.ssafy.trip.attraction.service;

import com.ssafy.trip.attraction.dto.AttractionRecommendationResponse;
import com.ssafy.trip.attraction.repository.RecommandationRepository;
import com.ssafy.trip.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Log4j2
public class RecommandationServiceImpl implements RecommandationService {

    private final RecommandationRepository recommandationRepository;
    private final UserRepository userRepository;

    @Override
    public AttractionRecommendationResponse getRecommandations() {
        Long userId = getCurrentUserId();

        return null;
    }

    private Long getCurrentUserId() {
        String temp = SecurityContextHolder.getContext().getAuthentication().getName();
        Long userId = 1L;
        log.info("userId = {}", temp);
        if (!temp.equals("anonymousUser")) userId = Long.parseLong(temp);
        return userId;
    }

}
