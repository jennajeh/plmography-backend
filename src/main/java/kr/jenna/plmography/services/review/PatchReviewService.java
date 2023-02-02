package kr.jenna.plmography.services.review;

import kr.jenna.plmography.exceptions.InvalidUser;
import kr.jenna.plmography.exceptions.ReviewNotFound;
import kr.jenna.plmography.models.Review;
import kr.jenna.plmography.models.vo.ReviewBody;
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

    public Review modify(Long userId, Long reviewId, Long starRate, ReviewBody reviewBody) {
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new ReviewNotFound());

        if (!review.isWriter(userId)) {
            throw new InvalidUser();
        }

        review.modify(starRate, reviewBody);

        return review;
    }
}
