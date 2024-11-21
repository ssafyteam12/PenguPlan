package com.ssafy.trip.review.service;

import com.ssafy.trip.review.dto.ReviewDTO;

import java.util.List;

public interface ReviewService {
    ReviewDTO createReview(ReviewDTO reviewDTO);
    ReviewDTO getReviewById(Long id);
    List<ReviewDTO> getReviewsByAttractionNo(Integer attractionNo);
    List<ReviewDTO> getReviewsByUserId(String userId);
    ReviewDTO updateReview(Long id, ReviewDTO reviewDTO);
    void deleteReview(Long id);
    ReviewDTO incrementUsefulVotes(Long id);
}