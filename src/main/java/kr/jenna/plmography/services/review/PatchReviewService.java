package kr.jenna.plmography.services.review;

import kr.jenna.plmography.exceptions.InvalidUser;
import kr.jenna.plmography.models.Review;
import kr.jenna.plmography.models.vo.ReviewBody;
import kr.jenna.plmography.repositories.ReviewCommentRepository;
import kr.jenna.plmography.repositories.ReviewRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PatchReviewService {
    private final ReviewRepository reviewRepository;
    private final ReviewCommentRepository reviewCommentRepository;

    public PatchReviewService(ReviewRepository reviewRepository,
                              ReviewCommentRepository reviewCommentRepository) {
        this.reviewRepository = reviewRepository;
        this.reviewCommentRepository = reviewCommentRepository;
    }

    public Review modify(Long userId, Long reviewId, Long starRate, ReviewBody reviewBody) {
        Review review = reviewRepository.getReferenceById(reviewId);

        if (!review.isWriter(userId)) {
            throw new InvalidUser();
        }

        review.modify(starRate, reviewBody);

        return review;
    }
}
