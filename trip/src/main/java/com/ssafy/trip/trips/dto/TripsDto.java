package com.ssafy.trip.trips.dto;

import jakarta.persistence.Column;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TripsDto {
    private Integer tripId;
    private Integer userId;
    private String content;
    private Integer sidoCode;
    private LocalDate startDate;
    private LocalDate endDate;
    private Boolean isPublic;
    private Integer copiedCount;
    private List<AttractionDetail> attractions;


    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class AttractionDetail {
        private int no;
        private String title;
        private String image;
        private String address;
        private BigDecimal latitude;
        private BigDecimal longitude;
        private int day;
        private int sequence;
    }


    public static TripsDto of (TripsRequest tripsRequest, Integer userId) {
        return TripsDto.builder()
                .userId(userId)
                .tripId(tripsRequest.getTripId())
                .content(tripsRequest.getContent())
                .sidoCode(tripsRequest.getSidoCode())
                .startDate(tripsRequest.getStartDate())
                .endDate(tripsRequest.getEndDate())
                .attractions(tripsRequest.getAttractions())
                .build();
    }
}

