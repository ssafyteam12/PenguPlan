package com.ssafy.trip.trips.repository;

import com.ssafy.trip.trips.dto.TripsDto;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface TripsMapper {
    void insertTrip(TripsDto tripsDto);

    void insertTripAttractions(@Param("tripId") int tripId,
                               @Param("tripAttractions") List<TripsDto.AttractionDetail> attractions);

    TripsDto getTripById(int tripId);

    void updateTrip(@Param("tripId") int tripId, @Param("tripsDto") TripsDto tripsDto);

    void deleteTripAttraction(int tripId);

    void deleteTrip(int tripId);

    List<TripsDto> getAllTrips(int offset, int size);

    void togglePublic(int tripId);
}