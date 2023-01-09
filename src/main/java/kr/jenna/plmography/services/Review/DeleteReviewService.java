package kr.jenna.plmography.services.Review;

import kr.jenna.plmography.exceptions.InvalidUser;
import kr.jenna.plmography.exceptions.ReviewNotFound;
import kr.jenna.plmography.exceptions.UnmodifiableReview;
import kr.jenna.plmography.models.Comment;
import kr.jenna.plmography.models.Review;
import kr.jenna.plmography.models.VO.PostId;
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

    public void delete(Long userId, PostId postId) {
        Review review = reviewRepository.findById(postId.getValue())
                .orElseThrow(() -> new ReviewNotFound());

        List<Comment> comments = commentRepository.findAllByPostId(postId);

        if (!review.isWriter(userId)) {
            throw new InvalidUser();
        }

        if (comments.size() > 0) {
            throw new UnmodifiableReview();
        }

        reviewRepository.delete(review);
    }
}
