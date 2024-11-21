package com.ssafy.trip.attractionLikes.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LikeRequest {
    private Long userId;
    private Integer attractionNo;
}
