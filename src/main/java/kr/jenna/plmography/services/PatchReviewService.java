package kr.jenna.plmography.services;

import kr.jenna.plmography.dtos.ReviewRegistrationDto;
import kr.jenna.plmography.models.Review;
import kr.jenna.plmography.models.ReviewBody;
import kr.jenna.plmography.repositories.ReviewRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PatchReviewService {
    private final ReviewRepository reviewRepository;

    public PatchReviewService(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    public void update(ReviewRegistrationDto reviewRegistrationDto,
                       Long reviewId) {
        Review review = reviewRepository.getReferenceById(reviewId);

        review.update(new ReviewBody(reviewRegistrationDto.getReviewBody()));
    }
}
