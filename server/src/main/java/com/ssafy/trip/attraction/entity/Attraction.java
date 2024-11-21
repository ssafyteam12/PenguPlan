package com.ssafy.trip.attraction.entity;

import com.ssafy.trip.attraction.dto.AttractionDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "attractions",
        indexes = {
                @Index(name = "attractions_sido_to_sidos_code_fk_idx", columnList = "area_code"),
                @Index(name = "attractions_sigungu_to_guguns_gugun_fk_idx", columnList = "si_gun_gu_code"),
                @Index(name = "attractions_typeid_to_types_typeid_fk_idx", columnList = "content_type_id")
        })
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Attraction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer no;

    @Column(name = "content_id")
    private Integer contentId;

    @Column(length = 255)
    private String title;

    @Column(name = "content_type_id")
    private Integer contentTypeId;

    @Column(name = "area_code")
    private Integer areaCode;

    @Column(name = "si_gun_gu_code")
    private Integer siGunGuCode;

    @Column(name = "first_image1", length = 255)
    private String firstImage1;

    @Column(name = "first_image2", length = 100)
    private String firstImage2;

    @Column(name = "map_level")
    private Integer mapLevel;

    @Column(precision = 38, scale = 2)
    private BigDecimal latitude;

    @Column(precision = 38, scale = 2)
    private BigDecimal longitude;

    @Column(length = 20)
    private String tel;

    @Column(length = 255)
    private String addr1;

    @Column(length = 100)
    private String addr2;

    @Column(length = 1000)
    private String homepage;

    @Column(length = 10000)
    private String overview;


    public static Attraction from(AttractionDTO attraction) {
        return Attraction.builder()
                .no(attraction.getNo())
                .contentId(attraction.getContentId())
                .title(attraction.getTitle())
                .contentTypeId(attraction.getContentTypeId())
                .areaCode(attraction.getAreaCode())
                .siGunGuCode(attraction.getSiGunGuCode())
                .firstImage1(attraction.getFirstImage1())
                .firstImage2(attraction.getFirstImage2())
                .mapLevel(attraction.getMapLevel())
                .latitude(attraction.getLatitude())
                .longitude(attraction.getLongitude())
                .tel(attraction.getTel())
                .addr1(attraction.getAddr1())
                .addr2(attraction.getAddr2())
                .homepage(attraction.getHomepage())
                .overview(attraction.getOverview())
                .build();
    }
}