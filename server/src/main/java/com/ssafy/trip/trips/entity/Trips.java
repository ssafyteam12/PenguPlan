package com.ssafy.trip.trips.entity;

import com.ssafy.trip.user.entity.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "trips",
        indexes = {
                @Index(name = "user_id", columnList = "user_id")
        })
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Trips {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "trip_id")
    private Long tripId;

    @Column(name="user_id", nullable = false)
    private Long userId;

    @Column(length = 1000, nullable = false)
    private String content;

    @Column(name = "sido_code", nullable = false)
    private Integer sidoCode;

    @Column(nullable = false)
    private LocalDate startDate;

    @Column(nullable = false)
    private LocalDate endDate;

    @Column(nullable = false)
    private Boolean isPublic;
}