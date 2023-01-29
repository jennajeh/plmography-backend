package kr.jenna.plmography.services.Review;

import kr.jenna.plmography.dtos.Like.LikeUserIdsDto;
import kr.jenna.plmography.exceptions.ReviewNotFound;
import kr.jenna.plmography.models.Review;
import kr.jenna.plmography.models.VO.LikeUserId;
import kr.jenna.plmography.models.VO.ReviewId;
import kr.jenna.plmography.repositories.ReviewRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Collectors;

@Service
@Transactional
public class ToggleReviewLikeService {
    private ReviewRepository reviewRepository;

    public ToggleReviewLikeService(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }


    public LikeUserIdsDto toggleLike(ReviewId reviewId, LikeUserId likeUserId) {
        Review review = reviewRepository.findById(reviewId.getValue())
                .orElseThrow(() -> new ReviewNotFound());

        review.toggleLike(likeUserId);

        return new LikeUserIdsDto(review.getLikeUserIds()
                .stream()
                .map(id -> id.toDto())
                .collect(Collectors.toSet()));
    }
}
