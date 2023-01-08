package kr.jenna.plmography.services;

import kr.jenna.plmography.dtos.ReviewRegistrationDto;
import kr.jenna.plmography.exceptions.UserNotFound;
import kr.jenna.plmography.models.ContentId;
import kr.jenna.plmography.models.Review;
import kr.jenna.plmography.models.ReviewBody;
import kr.jenna.plmography.models.User;
import kr.jenna.plmography.models.UserId;
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

        Long contendId = reviewRegistrationDto.getContentId();
        Long starRate = reviewRegistrationDto.getStarRate();
        String reviewBody = reviewRegistrationDto.getReviewBody();

        Review review = new Review(new UserId(user.getId()), new ContentId(contendId),
                starRate, new ReviewBody(reviewBody));

        reviewRepository.save(review);

        return review;
    }
}
