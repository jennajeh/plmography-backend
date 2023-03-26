package kr.jenna.plmography.services.review;

import kr.jenna.plmography.dtos.review.ReviewRegistrationDto;
import kr.jenna.plmography.exceptions.UserNotFound;
import kr.jenna.plmography.models.Review;
import kr.jenna.plmography.models.User;
import kr.jenna.plmography.models.vo.ContentId;
import kr.jenna.plmography.models.vo.ReviewBody;
import kr.jenna.plmography.models.vo.UserId;
import kr.jenna.plmography.repositories.ReviewRepository;
import kr.jenna.plmography.repositories.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

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

    public Review create(Long id, ReviewRegistrationDto reviewRegistrationDto) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFound(id));

        List<Review> notDeletedReviews = reviewRepository.findAllByUserIdAndContentIdAndIsDeleted(
                new UserId(user.getId()), new ContentId(reviewRegistrationDto.getContentId()));

        if (!notDeletedReviews.isEmpty()) {
            return notDeletedReviews.get(0);
        }

        UserId userId = new UserId(user.getId());
        ContentId contentId = new ContentId(reviewRegistrationDto.getContentId());
        Long starRate = reviewRegistrationDto.getStarRate();
        ReviewBody reviewBody = new ReviewBody(reviewRegistrationDto.getReviewBody());

        Review review = new Review(userId, contentId, starRate, reviewBody, LocalDateTime.now(), LocalDateTime.now());

        reviewRepository.save(review);

        return review;
    }
}
