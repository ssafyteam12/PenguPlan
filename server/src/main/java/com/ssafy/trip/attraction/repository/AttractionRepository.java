package com.ssafy.trip.attraction.repository;

import com.ssafy.trip.attraction.dto.AttractionDTO;
import com.ssafy.trip.attraction.dto.AttractionDetailDTO;
import com.ssafy.trip.attraction.entity.Attraction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AttractionRepository extends JpaRepository<Attraction, Long> {

    @Query(value = "SELECT * FROM attractions a " +
            "WHERE a.latitude BETWEEN :minLatitude AND :maxLatitude " +
            "AND a.longitude BETWEEN :minLongitude AND :maxLongitude " +
            "ORDER BY ABS((:maxLongitude + :minLongitude) / 2 - a.longitude) + ABS((:maxLatitude + :minLatitude) / 2 - a.latitude)" +
            "LIMIT 50",
            nativeQuery = true)
    List<Attraction> findAttractionsByPosition(
            @Param("minLatitude") double minLatitude,
            @Param("maxLatitude") double maxLatitude,
            @Param("minLongitude") double minLongitude,
            @Param("maxLongitude") double maxLongitude);


    @Query(value = "SELECT a.* " +
            "FROM attractions a " +
            "WHERE (:sido = -1 OR a.area_code = :sido) AND " +
            "(:gugun = -1 OR a.si_gun_gu_code = :gugun) AND " +
            "(:content = -1 OR a.content_type_id = :content) AND" +
            "(:keyword IS NULL OR :keyword = '' OR a.title LIKE CONCAT('%', :keyword, '%')) " +
            "order by rand() " +
            "LIMIT 50",
            nativeQuery = true)
    List<Attraction> findAttractionsBySearch(@Param("sido") int sido,
                                             @Param("gugun") int gugun,
                                             @Param("content") int content,
                                             @Param("keyword") String keyword);

    @Query(value = "SELECT *, " +
            "(6371 * acos(cos(radians(:latitude)) * cos(radians(a.latitude)) * " +
            "cos(radians(a.longitude) - radians(:longitude)) + " +
            "sin(radians(:latitude)) * sin(radians(a.latitude)))) AS distance " +
            "FROM attractions a " +
            "HAVING distance <= :distance " +
            "ORDER BY distance " +
            "LIMIT 30",
            nativeQuery = true)
    List<Attraction> findNearbyAttractions(
            @Param("latitude") double latitude,
            @Param("longitude") double longitude,
            @Param("distance") double distance
    );

    Attraction findByNo(Integer no);


    boolean existsByNo(int attractionNo);
}