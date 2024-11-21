package com.ssafy.trip.tripAttractions.repository;

import com.ssafy.trip.tripAttractions.entity.TripAttractions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TripAttractionsRepository extends JpaRepository<TripAttractions, Long> {
}
