package com.ssafy.trip.attraction.repository;


import com.ssafy.trip.attraction.entity.Attraction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecommandationRepository extends JpaRepository<Attraction, Long> {

}
