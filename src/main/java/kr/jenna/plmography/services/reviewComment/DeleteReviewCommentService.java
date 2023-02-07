package kr.jenna.plmography.services.reviewComment;

import kr.jenna.plmography.exceptions.InvalidUser;
import kr.jenna.plmography.exceptions.ReviewCommentNotFound;
import kr.jenna.plmography.models.ReviewComment;
import kr.jenna.plmography.repositories.ReviewCommentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class DeleteReviewCommentService {
    private final ReviewCommentRepository reviewCommentRepository;

    public DeleteReviewCommentService(ReviewCommentRepository reviewCommentRepository) {
        this.reviewCommentRepository = reviewCommentRepository;
    }

    public void delete(Long userId, Long commentId) {
        ReviewComment reviewComment = reviewCommentRepository.findById(commentId)
                .orElseThrow(() -> new ReviewCommentNotFound());

        if (!reviewComment.isWriter(userId)) {
            throw new InvalidUser();
        }

        reviewComment.delete();
    }
}
