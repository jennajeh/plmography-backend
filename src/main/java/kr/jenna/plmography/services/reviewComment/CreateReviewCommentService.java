package kr.jenna.plmography.services.reviewComment;

import kr.jenna.plmography.dtos.reviewComment.ReviewCommentRegistrationDto;
import kr.jenna.plmography.exceptions.UserNotFound;
import kr.jenna.plmography.models.ReviewComment;
import kr.jenna.plmography.models.User;
import kr.jenna.plmography.models.vo.PostId;
import kr.jenna.plmography.models.vo.ReviewCommentBody;
import kr.jenna.plmography.models.vo.UserId;
import kr.jenna.plmography.repositories.ReviewCommentRepository;
import kr.jenna.plmography.repositories.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@Transactional
public class CreateReviewCommentService {
    private final UserRepository userRepository;
    private final ReviewCommentRepository reviewCommentRepository;

    public CreateReviewCommentService(UserRepository userRepository,
                                      ReviewCommentRepository reviewCommentRepository) {
        this.userRepository = userRepository;
        this.reviewCommentRepository = reviewCommentRepository;
    }

    public ReviewComment create(Long userId, ReviewCommentRegistrationDto commentRegistrationDto) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFound(userId));

        ReviewComment reviewComment = new ReviewComment(
                new UserId(commentRegistrationDto.getUserId()),
                new PostId(commentRegistrationDto.getPostId()),
                new ReviewCommentBody(commentRegistrationDto.getReviewCommentBody()),
                LocalDateTime.now(),
                LocalDateTime.now());

        reviewCommentRepository.save(reviewComment);

        return reviewComment;
    }
}
