package kr.jenna.plmography.services.reviewComment;

import kr.jenna.plmography.dtos.reviewComment.ReviewCommentDto;
import kr.jenna.plmography.dtos.reviewComment.ReviewCommentsDto;
import kr.jenna.plmography.dtos.user.WriterDto;
import kr.jenna.plmography.exceptions.UserNotFound;
import kr.jenna.plmography.models.ReviewComment;
import kr.jenna.plmography.models.User;
import kr.jenna.plmography.repositories.ReviewCommentRepository;
import kr.jenna.plmography.repositories.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class GetReviewCommentsService {
    private final ReviewCommentRepository reviewCommentRepository;
    private final UserRepository userRepository;

    public GetReviewCommentsService(ReviewCommentRepository reviewCommentRepository,
                                    UserRepository userRepository) {
        this.reviewCommentRepository = reviewCommentRepository;
        this.userRepository = userRepository;
    }

    public ReviewCommentsDto list() {
        List<ReviewComment> reviewComments = reviewCommentRepository.findAll();

        List<ReviewCommentDto> reviewCommentDtos = reviewComments.stream()
                .map(comment -> {
                    User user = userRepository.findById(comment.getUserId().getValue())
                            .orElseThrow(() -> new UserNotFound(comment.getUserId().getValue()));

                    return new ReviewCommentDto(
                            comment.getId(),
                            new WriterDto(
                                    user.getId(),
                                    user.getNickname().getValue(),
                                    user.getProfileImage().getValue()),
                            comment.getPostId() == null
                                    ? 0
                                    : comment.getPostId().getValue(),
                            comment.getReviewCommentBody().getValue(),
                            comment.isDeleted(),
                            comment.getCreatedAt(),
                            comment.getUpdatedAt());
                }).collect(Collectors.toList());

        return new ReviewCommentsDto(reviewCommentDtos);
    }
}
