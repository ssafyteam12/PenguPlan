package com.ssafy.trip.attraction.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class AttractionRecommendationResponse {
    private List<AttractionDTO> recommendations;
}