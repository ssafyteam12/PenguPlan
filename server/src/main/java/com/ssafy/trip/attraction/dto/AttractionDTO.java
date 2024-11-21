package com.ssafy.trip.attraction.dto;

import com.ssafy.trip.attraction.entity.Attraction;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AttractionDTO {
    private int no;
    private Integer contentId;
    private String title;
    private Integer contentTypeId;
    private Integer areaCode;
    private Integer siGunGuCode;
    private String firstImage1;
    private String firstImage2;
    private Integer mapLevel;
    private BigDecimal latitude;
    private BigDecimal longitude;
    private String tel;
    private String addr1;
    private String addr2;
    private String homepage;
    private String overview;
    private Boolean isLiked;
    private Integer avgLank;

    public static AttractionDTO from(Attraction attraction) {
        return AttractionDTO.builder()
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