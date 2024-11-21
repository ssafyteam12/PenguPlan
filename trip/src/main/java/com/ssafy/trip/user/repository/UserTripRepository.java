package com.ssafy.trip.user.repository;


import com.ssafy.trip.attraction.entity.Attraction;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserTripRepository extends JpaRepository<Attraction, Long> {

    //tripid를 받아서 그에 해당하는 Attraction리스트를 반환
    @Query("SELECT a " +
            "FROM Attraction a JOIN TripAttractions ta ON a.no = ta.attraction.no " +
            "WHERE ta.trip.tripId = :tripId " +
            "ORDER BY ta.day")
    List<Attraction> findByTripId(@Param("tripId") Long tripId);
}