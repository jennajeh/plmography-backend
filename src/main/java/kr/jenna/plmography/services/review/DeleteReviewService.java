package kr.jenna.plmography.services.review;

import kr.jenna.plmography.exceptions.InvalidUser;
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
        Review review = reviewRepository.getReferenceById(reviewId);

        if (!review.isWriter(userId)) {
            throw new InvalidUser();
        }

        if (commentRepository.existsByPostId(new PostId(reviewId))) {
            List<Comment> comments = commentRepository.findAllByPostId(new PostId(reviewId));

            for (int i = 0; i < comments.size(); i += 1) {
                comments.get(i).delete();
            }
        }

        review.delete();
    }
}
