package com.ssafy.trip.tripAttractions.entity;

import com.ssafy.trip.attraction.entity.Attraction;
import com.ssafy.trip.trips.entity.Trips;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "trip_attractions",
        indexes = {
                @Index(name = "trip_id", columnList = "trip_id"),
                @Index(name = "attraction_no", columnList = "attraction_no")
        })
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TripAttractions {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "trip_attraction_id")
    private Integer tripAttractionId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "trip_id", nullable = false)
    private Trips trip;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "attraction_no", nullable = false)
    private Attraction attraction;

    @Column(nullable = false)
    private Integer day;

    @Column(nullable = false)
    private Integer sequence;
}