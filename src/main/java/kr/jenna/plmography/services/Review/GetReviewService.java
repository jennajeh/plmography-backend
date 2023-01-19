package kr.jenna.plmography.services.Review;

import kr.jenna.plmography.dtos.Review.MyReviewsDto;
import kr.jenna.plmography.dtos.Review.ReviewDto;
import kr.jenna.plmography.dtos.User.WriterDto;
import kr.jenna.plmography.exceptions.UserNotFound;
import kr.jenna.plmography.models.Review;
import kr.jenna.plmography.models.User;
import kr.jenna.plmography.models.VO.LikeUserId;
import kr.jenna.plmography.models.VO.UserId;
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
    private final UserRepository userRepository;

    public GetReviewService(ReviewRepository reviewRepository,
                            UserRepository userRepository) {
        this.reviewRepository = reviewRepository;
        this.userRepository = userRepository;
    }

    public MyReviewsDto myReview(Long userId) {
        List<Review> reviews = reviewRepository.findAllByUserId(new UserId(userId));

        List<ReviewDto> reviewDtos = reviews.stream()
                .map(review -> {
                    User user = userRepository.findById(review.getUserId().getValue())
                            .orElseThrow(() -> new UserNotFound(review.getUserId().getValue()));

                    return new ReviewDto(
                            review.getId(),
                            new WriterDto(
                                    user.getId(),
                                    user.getNickname().getValue(),
                                    user.getProfileImage().getValue()),
                            review.getContentId().getValue(),
                            review.getStarRate(),
                            review.getReviewBody().getValue(),
                            review.getLikeUserIds()
                                    .stream()
                                    .map(LikeUserId::toDto)
                                    .collect(Collectors.toSet()),
                            review.getDeleted(),
                            review.getCreatedAt(),
                            review.getUpdatedAt());
                }).toList();

        return new MyReviewsDto(reviewDtos);
    }
}
