package com.ssafy.trip.attraction.dto;

import com.ssafy.trip.review.dto.ReviewDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AttractionDetailDTO {
    private AttractionDTO attraction;
    private List<ReviewDTO> reviews;
    private String aiDescription;
    private boolean isLiked; // 현재 사용자가 좋아요 했는지 여부

    public static AttractionDetailDTO of(AttractionDTO attraction, List<ReviewDTO> reviews, String aiDescription, boolean isLiked) {
        return AttractionDetailDTO.builder()
                .attraction(attraction)
                .reviews(reviews)
                .aiDescription(aiDescription)
                .isLiked(isLiked)
                .build();
    }
}



