package kr.jenna.plmography.services.Review;

import kr.jenna.plmography.dtos.Review.ReviewDto;
import kr.jenna.plmography.exceptions.InvalidUser;
import kr.jenna.plmography.exceptions.ReviewNotFound;
import kr.jenna.plmography.exceptions.UnmatchedPostId;
import kr.jenna.plmography.models.Review;
import kr.jenna.plmography.models.VO.PostId;
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

    public Review modify(Long userId, PostId postId, ReviewDto reviewDto) {
        Review review = reviewRepository.findById(postId.getValue())
                .orElseThrow(() -> new ReviewNotFound());

        if (!review.isWriter(userId)) {
            throw new InvalidUser();
        }

        if (!postId.equals(new PostId(reviewDto.getId()))) {
            throw new UnmatchedPostId();
        }

        review.modify(reviewDto);

        return review;
    }
}
