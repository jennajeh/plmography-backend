package kr.jenna.plmography.services.Review;

import kr.jenna.plmography.exceptions.InvalidUser;
import kr.jenna.plmography.exceptions.ReviewNotFound;
import kr.jenna.plmography.models.Review;
import kr.jenna.plmography.models.VO.ReviewBody;
import kr.jenna.plmography.repositories.CommentRepository;
import kr.jenna.plmography.repositories.ReviewRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PatchReviewService {
    private final ReviewRepository reviewRepository;
    private final CommentRepository commentRepository;

    public PatchReviewService(ReviewRepository reviewRepository,
                              CommentRepository commentRepository) {
        this.reviewRepository = reviewRepository;
        this.commentRepository = commentRepository;
    }

    public Review modify(Long userId, Long reviewId, ReviewBody reviewBody) {
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new ReviewNotFound());

        if (!review.isWriter(userId)) {
            throw new InvalidUser();
        }

        review.modify(reviewBody);

        return review;
    }
}
