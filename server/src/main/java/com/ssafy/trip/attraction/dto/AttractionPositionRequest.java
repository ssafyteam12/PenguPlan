package com.ssafy.trip.attraction.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AttractionPositionRequest {
    private Integer sido;
    private Integer gugun;
    private Integer content;
    private String keyword;
    private Double minLatitude;
    private Double minLongitude;
    private Double maxLatitude;
    private Double maxLongitude;
}