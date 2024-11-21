package com.ssafy.trip.trips.repository;

import com.ssafy.trip.trips.entity.Trips;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TripsRepository extends JpaRepository<Trips, Long> {
    List<Trips> findByUserId(Long userId);
}
