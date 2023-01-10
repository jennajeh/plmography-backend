package kr.jenna.plmography.services.Review;

import kr.jenna.plmography.dtos.Review.ReviewRegistrationDto;
import kr.jenna.plmography.exceptions.UserNotFound;
import kr.jenna.plmography.models.Review;
import kr.jenna.plmography.models.User;
import kr.jenna.plmography.models.VO.ContentId;
import kr.jenna.plmography.models.VO.ReviewBody;
import kr.jenna.plmography.models.VO.UserId;
import kr.jenna.plmography.repositories.ReviewRepository;
import kr.jenna.plmography.repositories.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CreateReviewService {
    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;

    public CreateReviewService(ReviewRepository reviewRepository,
                               UserRepository userRepository) {
        this.reviewRepository = reviewRepository;
        this.userRepository = userRepository;
    }

    public Review create(Long userId, ReviewRegistrationDto reviewRegistrationDto) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFound(userId));

        ContentId contentId = new ContentId(reviewRegistrationDto.getContentId());
        Long starRate = reviewRegistrationDto.getStarRate();
        ReviewBody reviewBody = new ReviewBody(reviewRegistrationDto.getReviewBody());

        Review review = new Review(new UserId(userId), contentId, starRate, reviewBody);

        reviewRepository.save(review);

        return review;
    }
}
