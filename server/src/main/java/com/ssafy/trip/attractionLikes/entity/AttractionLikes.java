package com.ssafy.trip.attractionLikes.entity;

import com.ssafy.trip.attraction.entity.Attraction;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "attraction_likes")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AttractionLikes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "like_id")
    private Integer likeId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "attraction_no", nullable = false)
    private Attraction attraction;

    @Column(name = "user_id", nullable = false)
    private Long userId;

}