package com.ssafy.trip.user.dto;


import com.ssafy.trip.attraction.dto.AttractionDTO;
import com.ssafy.trip.attractionLikes.entity.AttractionLikes;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserLikesResponse {
    private Long userId;
    private List<AttractionDTO> likedAttractions;
    
    // Additional useful information
    private int totalLikes;
//    private List<String> topCategories; // Most liked attraction categories
//    private List<String> recentlyLiked; // Recently liked attractions

    public static UserLikesResponse from(List<AttractionLikes> likes) {
        if (likes == null || likes.isEmpty()) {
            return UserLikesResponse.builder()
                    .userId(null)
                    .likedAttractions(List.of())
                    .totalLikes(0)
                    .build();
        }

        AttractionLikes firstLike = likes.get(0);
        return UserLikesResponse.builder()
                .userId(firstLike.getUserId().longValue())
                .likedAttractions(likes.stream()
                        .map(like -> AttractionDTO.from(like.getAttraction()))
                        .collect(Collectors.toList()))
                .totalLikes(likes.size())
                .build();
    }
}