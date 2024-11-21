package com.ssafy.trip.attractionLikes.repository;


import com.ssafy.trip.attractionLikes.entity.AttractionLikes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface AttractionLikesRepository extends JpaRepository<AttractionLikes, Long> {
    Optional<AttractionLikes> findByAttractionNoAndUserId(Integer attraction_no, Long userId);
    boolean existsByAttractionNoAndUserId(Integer attractionNo, Long userId);
    void deleteByAttractionNoAndUserId(Integer attractionNo, Long userId);
    long countByAttractionNo(Integer attractionNo);

    List<AttractionLikes> findByUserId(Long userId);

    AttractionLikes save(AttractionLikes attractionLikes);
}