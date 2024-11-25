package com.ssafy.trip.user.dto;

import com.ssafy.trip.attraction.dto.AttractionDTO;
import com.ssafy.trip.trips.dto.TripsDto;
import com.ssafy.trip.trips.entity.Trips;
import com.ssafy.trip.user.entity.User;
import lombok.*;

import java.util.List;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserTripsDto {
    private Long tripId;
    private Long userId;
    private String content;
    private int sidoCode;
    private String startDate;
    private String endDate;
    private Boolean isPublic;
    private List<AttractionDTO> attractions;


    public static UserTripsDto of(Trips trips, List<AttractionDTO> attractions) {
        UserTripsDto userTripsDto = new UserTripsDto();
        userTripsDto.setTripId(trips.getTripId());
        userTripsDto.setUserId(trips.getUserId());
        userTripsDto.setContent(trips.getContent());
        userTripsDto.setSidoCode(trips.getSidoCode());
        userTripsDto.setStartDate(trips.getStartDate().toString());
        userTripsDto.setEndDate(trips.getEndDate().toString());
        userTripsDto.setAttractions(attractions);
        userTripsDto.setIsPublic(trips.getIsPublic());

        return userTripsDto;
    }
}
