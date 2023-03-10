package kr.jenna.plmography.services.review;

import kr.jenna.plmography.dtos.review.MyReviewsDto;
import kr.jenna.plmography.dtos.review.ReviewDto;
import kr.jenna.plmography.dtos.user.WriterDto;
import kr.jenna.plmography.exceptions.UserNotFound;
import kr.jenna.plmography.models.Review;
import kr.jenna.plmography.models.User;
import kr.jenna.plmography.models.vo.LikeUserId;
import kr.jenna.plmography.models.vo.PostId;
import kr.jenna.plmography.models.vo.UserId;
import kr.jenna.plmography.repositories.ReviewCommentRepository;
import kr.jenna.plmography.repositories.ReviewRepository;
import kr.jenna.plmography.repositories.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class GetReviewService {
    private final ReviewRepository reviewRepository;
    private final ReviewCommentRepository reviewCommentRepository;
    private final UserRepository userRepository;

    public GetReviewService(ReviewRepository reviewRepository,
                            ReviewCommentRepository reviewCommentRepository,
                            UserRepository userRepository) {
        this.reviewRepository = reviewRepository;
        this.reviewCommentRepository = reviewCommentRepository;
        this.userRepository = userRepository;
    }

    public MyReviewsDto myReview(Long userId) {
        List<Review> reviews = reviewRepository.findAllByUserId(new UserId(userId));

        List<ReviewDto> reviewDtos = reviews.stream()
                .map(review -> {
                    User user = userRepository.findById(review.getUserId().getValue())
                            .orElseThrow(() -> new UserNotFound(review.getUserId().getValue()));

                    Long commentNumber = reviewCommentRepository.countByPostIdAndIsNotDeleted(new PostId(review.getId()));

                    return new ReviewDto(
                            review.getId(),
                            new WriterDto(
                                    user.getId(),
                                    user.getNickname().getValue(),
                                    user.getProfileImage().getValue()),
                            commentNumber,
                            review.getContentId() == null
                                    ? 0
                                    : review.getContentId().getValue(),
                            review.getStarRate(),
                            review.getReviewBody().getValue(),
                            review.getLikeUserIds()
                                    .stream()
                                    .map(LikeUserId::toDto)
                                    .collect(Collectors.toSet()),
                            review.getDeleted(),
                            review.getCreatedAt(),
                            review.getUpdatedAt());
                }).collect(Collectors.toList());

        return new MyReviewsDto(reviewDtos);
    }
}
