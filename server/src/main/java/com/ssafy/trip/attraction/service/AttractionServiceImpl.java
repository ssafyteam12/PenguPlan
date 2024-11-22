package com.ssafy.trip.attraction.service;

import com.ssafy.trip.attraction.dto.AttractionDTO;
import com.ssafy.trip.attraction.dto.AttractionDetailDTO;
import com.ssafy.trip.attraction.dto.NearbyAttractionsDTO;
import com.ssafy.trip.attraction.entity.Attraction;
import com.ssafy.trip.attraction.repository.AttractionRepository;
import com.ssafy.trip.attractionLikes.dto.LikeResponse;
import com.ssafy.trip.attractionLikes.entity.AttractionLikes;
import com.ssafy.trip.attractionLikes.repository.AttractionLikesRepository;
import com.ssafy.trip.review.dto.ReviewDTO;
import com.ssafy.trip.review.dto.ReviewRequest;
import com.ssafy.trip.review.dto.ReviewResponse;
import com.ssafy.trip.review.entity.Review;
import com.ssafy.trip.review.entity.ReviewRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class AttractionServiceImpl implements AttractionService {
    private final AttractionRepository attractionRepository;
    private final ReviewRepository reviewRepository;
    private final AttractionLikesRepository attractionLikesRepository;
    private final OpenAiChatModel openAiChatModel;

    public List<AttractionDTO> getAttractionsByPosition(double maxLat, double maxLong, double minLat, double minLong) {
        List<Attraction> attractions = attractionRepository.findAttractionsByPosition(minLat, maxLat, minLong, maxLong);
        Long userId = getCurrentUserId();
        return attractions.stream()
                .map(attraction -> {
                    AttractionDTO dto = AttractionDTO.from(attraction);

                    // Get average rating
                    Double avgRating = reviewRepository.getAverageRating(attraction.getNo().longValue());
                    dto.setAvgLank(avgRating != null ? avgRating.intValue() : 0);

                    // Check if liked
                    boolean isLiked = attractionLikesRepository.existsByAttractionNoAndUserId(attraction.getNo(), userId);
                    dto.setIsLiked(isLiked);

                    return dto;
                })
                .collect(Collectors.toList());
    }

    public List<AttractionDTO> getAttractionsBySearch(int sido, int gugun, int content, String keyword) {
        log.info(sido + " " + gugun + " " + content + " " + keyword);
        List<Attraction> attractions = attractionRepository.findAttractionsBySearch(sido, gugun, content, keyword);
        log.info("attractionsSize :" +  attractions.size());
        Long userId = getCurrentUserId();
        return attractions.stream()
                .map(attraction -> {
                    AttractionDTO dto = AttractionDTO.from(attraction);

                    // Get average rating
                    Double avgRating = reviewRepository.getAverageRating(attraction.getNo().longValue());
                    dto.setAvgLank(avgRating != null ? avgRating.intValue() : 0);

                    // Check if liked
                    boolean isLiked = attractionLikesRepository.existsByAttractionNoAndUserId(attraction.getNo(), userId);
                    dto.setIsLiked(isLiked);

                    return dto;
                })
                .toList();
    }

    public AttractionDetailDTO getAttractionDetail(int attractionNo) {
        AttractionDTO attraction =  AttractionDTO.from(attractionRepository.findByNo(attractionNo));
        List<ReviewDTO> reviews = reviewRepository.findByAttractionNo(attractionNo).stream().map(ReviewDTO::from).toList();
        boolean isLiked = attractionLikesRepository.existsByAttractionNoAndUserId(attractionNo, 1L);
        String aiDescription = attraction.getOverview();
        if (aiDescription == null) {
            aiDescription = generateAIDescription(attractionNo);
            attraction.setOverview(aiDescription);
            attractionRepository.save(Attraction.from(attraction));
        }
        return AttractionDetailDTO.of(attraction, reviews, aiDescription, isLiked);
    }

    public NearbyAttractionsDTO getNearbyAttractions(int attractionNo) {
        AttractionDTO attraction = AttractionDTO.from(attractionRepository.findByNo(attractionNo));
        return new NearbyAttractionsDTO(
                attractionRepository.findNearbyAttractions(
                        attraction.getLatitude().doubleValue(),
                        attraction.getLongitude().doubleValue(),
                        5.0 // 5km 반경
                )
        );
    }

//
//    public RatingDTO addRating(int attractionNo, RatingRequest request) {
//        validateAttraction(attractionNo);
//        return ratingRepository.save(request.toEntity(attractionNo));
//    }

    public ReviewResponse addComment(int attractionNo, ReviewRequest request) {
        existsAttraction(attractionNo);
        return ReviewResponse.from(reviewRepository.save(Review.from(request)));
    }

    @Override
    @Transactional
    public LikeResponse toggleLike(Integer attractionNo) {

        Long userId = getCurrentUserId();

        // 먼저 attraction 조회
        Attraction attraction = attractionRepository.findById(attractionNo.longValue())
                .orElseThrow(() -> new RuntimeException("관광지를 찾을 수 없습니다."));

        // 좋아요 존재 여부 확인
        boolean exists = attractionLikesRepository.existsByAttractionNoAndUserId(attractionNo, userId);

        if (exists) {
            // 이미 좋아요가 있으면 삭제
            attractionLikesRepository.deleteByAttractionNoAndUserId(attractionNo, userId);
            return LikeResponse.builder()
                    .attractionNo(attractionNo)
                    .userId(userId)
                    .build();
        } else {
            // 좋아요가 없으면 생성
            AttractionLikes newLike = AttractionLikes.builder()
                    .attraction(attraction)
                    .userId(userId)
                    .build();

            AttractionLikes savedLike = attractionLikesRepository.save(newLike);
            return LikeResponse.builder()
                    .likeId(savedLike.getLikeId())
                    .attractionNo(attractionNo)
                    .userId(userId)
                    .build();
        }
    }

    @Override
    public void existsAttraction(int attractionNo) {
        if (!attractionRepository.existsByNo(attractionNo)) {
            throw new RuntimeException("관광지를 찾을 수 없습니다.");
        }
    }

    // AI 설명 생성 메소드 추가
    public String generateAIDescription(int attractionNo) {
        HashMap<Integer, String> tourTypes = new HashMap<>();
        tourTypes.put(1, "관광지");
        tourTypes.put(2, "문화시설");
        tourTypes.put(3, "축제공연행사");
        tourTypes.put(4, "여행코스");
        tourTypes.put(5, "레포츠");
        tourTypes.put(6, "숙박");
        tourTypes.put(7, "쇼핑");
        tourTypes.put(8, "음식점");
        AttractionDTO attraction = AttractionDTO.from(attractionRepository.findByNo(attractionNo));
        String result = "";
        String prompt = String.format("다음 여행지에 대해 200자 이내로 간략한 설명을 작성해주세요. 여행지의 특징, 볼거리, 매력을 포함해 주세요. 마지막엔 예상 소요시간을 간단히 언급해주세요(예시: 예상소요시간: 20분). 여행지: %s, 주소: %s, 여행지카테고리: %s",
                attraction.getTitle(),
                attraction.getAddr1(),
                tourTypes.get(attraction.getContentTypeId())
        );


        try {
            result += openAiChatModel.call(prompt);
        } catch (Exception e) {
            return "AI 설명 생성 중 오류가 발생했습니다. 기본 설명을 참고해주세요.";
        }
        return result;
    }

    private Long getCurrentUserId() {
        return Long.parseLong(SecurityContextHolder.getContext().getAuthentication().getName());
//        return 1L;
    }
}