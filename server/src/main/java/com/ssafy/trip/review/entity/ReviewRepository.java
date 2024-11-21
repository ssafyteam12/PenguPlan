package com.ssafy.trip.review.entity;

import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findByAttractionNoOrderByCreatedAtDesc(Integer attractionNo);

    @Query("SELECT AVG(r.rating) FROM Review r WHERE r.attractionNo = :attractionNo")
    Double getAverageRating(@Param("attraction") Long attractionNo);

    List<Review> findByUserName(String userName);

    List<Review> findByAttractionNo(Integer attractionNo);

}