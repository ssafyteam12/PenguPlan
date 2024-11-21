package com.ssafy.trip.attraction.dto;

import com.ssafy.trip.attraction.entity.Attraction;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NearbyAttractionsDTO {
    private List<Attraction> nearbyAttractions;
}