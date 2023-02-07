package kr.jenna.plmography.services.review;

import kr.jenna.plmography.exceptions.InvalidUser;
import kr.jenna.plmography.models.Review;
import kr.jenna.plmography.models.ReviewComment;
import kr.jenna.plmography.models.vo.PostId;
import kr.jenna.plmography.repositories.ReviewCommentRepository;
import kr.jenna.plmography.repositories.ReviewRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class DeleteReviewService {
    private final ReviewRepository reviewRepository;
    private final ReviewCommentRepository reviewCommentRepository;

    public DeleteReviewService(ReviewRepository reviewRepository,
                               ReviewCommentRepository reviewCommentRepository) {
        this.reviewRepository = reviewRepository;
        this.reviewCommentRepository = reviewCommentRepository;
    }

    public void delete(Long userId, Long reviewId) {
        Review review = reviewRepository.getReferenceById(reviewId);

        if (!review.isWriter(userId)) {
            throw new InvalidUser();
        }

        if (reviewCommentRepository.existsByPostId(new PostId(reviewId))) {
            List<ReviewComment> reviewComments = reviewCommentRepository.findAllByPostId(new PostId(reviewId));

            for (int i = 0; i < reviewComments.size(); i += 1) {
                reviewComments.get(i).delete();
            }
        }

        review.delete();
    }
}
