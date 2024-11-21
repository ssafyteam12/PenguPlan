package com.ssafy.trip.trips.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AllTripsResponse {
    private int page;
    private int size;
    private List<TripsDto> trips;

}
