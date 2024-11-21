package com.ssafy.trip.review.service;

import com.ssafy.trip.review.dto.ReviewDTO;
import com.ssafy.trip.review.entity.Review;
import com.ssafy.trip.review.entity.ReviewRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;

    public ReviewServiceImpl(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    @Override
    @Transactional
    public ReviewDTO createReview(ReviewDTO reviewDTO) {
        Review review = convertToEntity(reviewDTO);
        Review savedReview = reviewRepository.save(review);
        return convertToDTO(savedReview);
    }

    @Override
    public ReviewDTO getReviewById(Long id) {
        Review review = reviewRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Review not found"));
        return convertToDTO(review);
    }

    @Override
    public List<ReviewDTO> getReviewsByAttractionNo(Integer attractionNo) {
        List<Review> reviews = reviewRepository.findByAttractionNo(attractionNo);
        return reviews.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public List<ReviewDTO> getReviewsByUserId(String userName) {
        List<Review> reviews = reviewRepository.findByUserName(userName);
        return reviews.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public ReviewDTO updateReview(Long id, ReviewDTO reviewDTO) {
        Review review = reviewRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Review not found"));
        updateReviewFromDTO(review, reviewDTO);
        Review updatedReview = reviewRepository.save(review);
        return convertToDTO(updatedReview);
    }

    @Override
    @Transactional
    public void deleteReview(Long id) {
        reviewRepository.deleteById(id);
    }

    @Override
    @Transactional
    public ReviewDTO incrementUsefulVotes(Long id) {
        Review review = reviewRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Review not found"));
        review.incrementUsefulVotes();
        Review updatedReview = reviewRepository.save(review);
        return convertToDTO(updatedReview);
    }

    private Review convertToEntity(ReviewDTO reviewDTO) {
        return Review.builder()
                .attractionNo(reviewDTO.getAttractionNo())
                .userName(reviewDTO.getUserName())
                .rating(reviewDTO.getRating())
                .content(reviewDTO.getContent())
                .build();
    }

    private ReviewDTO convertToDTO(Review review) {
        return ReviewDTO.builder()
                .id(review.getId())
                .attractionNo(review.getAttractionNo())
                .userName(review.getUserName())
                .rating(review.getRating())
                .content(review.getContent())
                .createdAt(review.getCreatedAt())
                .updatedAt(review.getUpdatedAt())
                .usefulVotes(review.getUsefulVotes())
                .build();
    }

    private void updateReviewFromDTO(Review review, ReviewDTO reviewDTO) {
        review.setRating(reviewDTO.getRating());
        review.setContent(reviewDTO.getContent());
    }
}