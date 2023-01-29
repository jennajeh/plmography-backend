package kr.jenna.plmography.services.review;

import kr.jenna.plmography.exceptions.InvalidUser;
import kr.jenna.plmography.exceptions.ReviewNotFound;
import kr.jenna.plmography.exceptions.UnmodifiableReview;
import kr.jenna.plmography.models.Comment;
import kr.jenna.plmography.models.Review;
import kr.jenna.plmography.models.vo.PostId;
import kr.jenna.plmography.repositories.CommentRepository;
import kr.jenna.plmography.repositories.ReviewRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class DeleteReviewService {
    private final ReviewRepository reviewRepository;
    private final CommentRepository commentRepository;

    public DeleteReviewService(ReviewRepository reviewRepository,
                               CommentRepository commentRepository) {
        this.reviewRepository = reviewRepository;
        this.commentRepository = commentRepository;
    }

    public void delete(Long userId, Long reviewId) {
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new ReviewNotFound());

        List<Comment> comments = commentRepository.findAllByPostId(new PostId(reviewId));

        if (!review.isWriter(userId)) {
            throw new InvalidUser();
        }

        if (comments.size() > 0) {
            throw new UnmodifiableReview();
        }

        review.delete();
    }
}
