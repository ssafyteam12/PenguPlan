package com.ssafy.trip.trips.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.flogger.Flogger;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;


@Data
public class TripsRequest {
    private int tripId;
    private String content;
    private int sidoCode;
    private LocalDate startDate;
    private LocalDate endDate;
    private List<TripsDto.AttractionDetail> attractions;


    @Getter
    @Setter
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
}
