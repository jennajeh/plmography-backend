package kr.jenna.plmography.services;

import kr.jenna.plmography.models.Review;
import kr.jenna.plmography.repositories.ReviewRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class DeleteReviewService {
    private final ReviewRepository reviewRepository;

    public DeleteReviewService(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    public void delete(Long reviewId) {
        Review review = reviewRepository.getReferenceById(reviewId);

        reviewRepository.delete(review);
    }
}
