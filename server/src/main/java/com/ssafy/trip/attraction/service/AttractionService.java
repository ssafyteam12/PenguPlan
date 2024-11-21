package com.ssafy.trip.attraction.service;

import com.ssafy.trip.attraction.dto.*;
import com.ssafy.trip.attractionLikes.dto.LikeRequest;
import com.ssafy.trip.attractionLikes.dto.LikeResponse;
import com.ssafy.trip.review.dto.ReviewRequest;
import com.ssafy.trip.review.dto.ReviewResponse;
import jakarta.persistence.criteria.CriteriaBuilder;

import java.util.List;

public interface AttractionService {
    /**
     * 위치 기반 관광지 검색
     */
    List<AttractionDTO> getAttractionsByPosition(double maxLatitude, double maxLongitude, double minLatitude, double minLongitude);

    /**
     * 조건 기반 관광지 검색
     */
    List<AttractionDTO> getAttractionsBySearch(int sido, int gugun, int content, String keyword);

    /**
     * 관광지 상세 정보 조회
     */
    AttractionDetailDTO getAttractionDetail(int attractionNo);

    /**
     * 주변 관광지 정보 조회
     */
    NearbyAttractionsDTO getNearbyAttractions(int attractionNo);

//    /**
//     * 관광지 평점 등록
//     */
//    RatingDTO addRating(int attractionNo, RatingRequest request);
//
    /**
     * 관광지 댓글 등록
     */
    ReviewResponse addComment(int attractionNo, ReviewRequest request);

    /**
     * 관광지 좋아요 토글
     */
    LikeResponse toggleLike(Integer attractionNo);

    /**
     * 관광지 존재 여부 확인
     */
    void existsAttraction(int attractionNo);
}