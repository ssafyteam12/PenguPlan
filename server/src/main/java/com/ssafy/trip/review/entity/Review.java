package com.ssafy.trip.review.entity;

import com.ssafy.trip.review.dto.ReviewDTO;
import com.ssafy.trip.review.dto.ReviewRequest;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "reviews")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long attractionNo;

    @Column(nullable = false)
    private String userName;

    @Column(nullable = false)
    private int rating;

    @Column(length = 1000)
    private String content;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "useful_votes")
    @Builder.Default
    private int usefulVotes = 0;

    // Helper methods

    public void incrementUsefulVotes() {
        this.usefulVotes++;
    }

    public static Review from(ReviewRequest request) {
        return Review.builder()
                .attractionNo(request.getAttractionNo())
                .userName(request.getUserName())
                .rating(request.getRating())
                .content(request.getContent())
                .build();

    }

}