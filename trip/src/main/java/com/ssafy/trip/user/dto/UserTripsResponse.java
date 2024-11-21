package com.ssafy.trip.user.dto;

import com.ssafy.trip.trips.dto.TripsDto;
import com.ssafy.trip.trips.entity.Trips;
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
public class UserTripsResponse {
    private Long userId;
    private List<UserTripsDto> trips;

    // You might want to add additional fields such as:
    private int totalTrips;
    private String mostVisitedCity;
    private String latestTripDate;

    public static UserTripsResponse from(List<UserTripsDto> trips) {
        if (trips == null || trips.isEmpty()) {
            return UserTripsResponse.builder()
                    .userId(null    )
                    .trips(List.of())
                    .totalTrips(0)
                    .build();
        }

        UserTripsDto firstTrip = trips.get(0);
        return UserTripsResponse.builder()
                .userId(firstTrip.getUserId())
                .trips(trips)
                .totalTrips(trips.size())
                .build();
    }
}

