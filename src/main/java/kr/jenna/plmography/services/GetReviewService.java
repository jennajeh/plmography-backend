package kr.jenna.plmography.services;

import kr.jenna.plmography.exceptions.ReviewNotFound;
import kr.jenna.plmography.models.Review;
import kr.jenna.plmography.repositories.ReviewRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class GetReviewService {
    private final ReviewRepository reviewRepository;

    public GetReviewService(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }


    public Review detail(Long userId) {
        Review review = reviewRepository.findById(userId)
                .orElseThrow(() -> new ReviewNotFound());

        return review;
    }
}
