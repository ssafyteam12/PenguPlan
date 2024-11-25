package com.ssafy.trip.attraction.controller;

import com.ssafy.trip.attraction.dto.*;
import com.ssafy.trip.attraction.service.AttractionService;
import com.ssafy.trip.attractionLikes.dto.LikeResponse;
import com.ssafy.trip.review.dto.ReviewRequest;
import com.ssafy.trip.review.dto.ReviewResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/attractions")
public class AttractionController {

    private final AttractionService attractionService;


    @GetMapping
    public ResponseEntity<ResponseAttractionsDTO> getAttractionsByPosition(
            @RequestParam(value = "sido", defaultValue = "-1") int sido,
            @RequestParam(value = "gugun", defaultValue = "-1") int gugun,
            @RequestParam(value = "content", defaultValue = "-1") int content,
            @RequestParam(value = "keyword", required = false) String keyword,
            @RequestParam(value="minLat", required = false) Double minLatitude,
            @RequestParam(value="minLong", required = false) Double minLongitude,
            @RequestParam(value="maxLat", required = false) Double maxLatitude,
            @RequestParam(value="maxLong", required = false) Double maxLongitude

    ) {
        AttractionPositionRequest request = AttractionPositionRequest.builder()
                .sido(sido)
                .gugun(gugun)
                .content(content)
                .keyword(keyword)
                .minLatitude(minLatitude)
                .minLongitude(minLongitude)
                .maxLatitude(maxLatitude)
                .maxLongitude(maxLongitude)
                .build();
        List<AttractionDTO> attractions = attractionService.getAttractionsByPosition(request).stream().toList();
        log.info("" + attractions.size());
        ResponseAttractionsDTO responseAttractionsDTO = new ResponseAttractionsDTO(attractions);
        return ResponseEntity.ok(responseAttractionsDTO);
    }

    @GetMapping("/search")
    public ResponseEntity<ResponseAttractionsDTO> getAttractionsBySearch(
            @RequestParam(value = "sido", defaultValue = "-1") int sido,
            @RequestParam(value = "gugun", defaultValue = "-1") int gugun,
            @RequestParam(value = "content", defaultValue = "-1") int content,
            @RequestParam(value = "keyword", required = false) String keyword) {
        log.info(sido + " " + gugun + " " + content + " " + "" + keyword);
        List<AttractionDTO> attractions = attractionService.getAttractionsBySearch(sido, gugun, content, keyword).stream().toList();
        ResponseAttractionsDTO responseAttractionsDTO = new ResponseAttractionsDTO(attractions);
        return ResponseEntity.ok(responseAttractionsDTO);
    }

    //관광지상세정보조회
    @GetMapping("/{attractionNo}")
    public ResponseEntity<AttractionDetailDTO> getAttractionDetail(@PathVariable int attractionNo) {
        AttractionDetailDTO attraction = attractionService.getAttractionDetail(attractionNo);
        return ResponseEntity.ok(attraction);
    }

    //주변관광지목록조회
    @GetMapping("/{attractionNo}/nearby")
    public ResponseEntity<NearbyAttractionsDTO> getNearbyAttractions(@PathVariable int attractionNo) {
        NearbyAttractionsDTO nearbyAttractions = attractionService.getNearbyAttractions(attractionNo);
        return ResponseEntity.ok(nearbyAttractions);
    }

//    //관광지 별점 등록
//    @PostMapping("/{attractionNo}/ratings")
//    public ResponseEntity<RatingDTO> addRating(
//            @PathVariable int attractionNo,
//            @RequestBody RatingRequest request) {
//        RatingDTO rating = attractionService.addRating(attractionNo, request);
//        return ResponseEntity.ok(rating);
//    }

    //관광지 댓글(리뷰) 등록
    @PostMapping("/{attractionNo}/comments")
    public ResponseEntity<ReviewResponse> addComment(
            @PathVariable int attractionNo,
            @RequestBody ReviewRequest request) {
        ReviewResponse comment = attractionService.addComment(attractionNo, request);
        return ResponseEntity.ok(comment);
    }

    //관광지 좋아요 등록
    @PostMapping("/{attractionNo}/likes")
    public ResponseEntity<LikeResponse> toggleLike(
            @PathVariable int attractionNo) {
        LikeResponse like = attractionService.toggleLike(attractionNo);
        log.info("check!!");
        return ResponseEntity.ok(like);
    }

//    @GetMapping("/recommendations")
//    public ResponseEntity<AttractionRecommendationResponse> getRecommendations(
//            @RequestParam Long userId,
//            @RequestParam(defaultValue = "10") int limit) {
//        return ResponseEntity.ok(recommendationService.getRecommendations(userId, limit));
//    }
}