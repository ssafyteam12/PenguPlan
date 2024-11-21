package com.ssafy.trip.review.dto;

import com.ssafy.trip.review.entity.Review;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class ReviewResponse {
    private Long id;
    private Long attractionNo;
    private String userName;
    private int rating;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private int usefulVotes;

    public static ReviewResponse from(Review review) {
        return ReviewResponse.builder()
                .id(review.getId())
                .attractionNo(review.getAttractionNo())
                .userName(review.getUserName())
                .rating(review.getRating())
                .content(review.getContent())
                .createdAt(review.getCreatedAt())
                .updatedAt(review.getUpdatedAt())
                .usefulVotes(review.getUsefulVotes())
                .build();
    }
}