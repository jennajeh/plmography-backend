package kr.jenna.plmography.controllers;

import kr.jenna.plmography.dtos.LikeUserIdsDto;
import kr.jenna.plmography.models.LikeUserId;
import kr.jenna.plmography.models.ReviewId;
import kr.jenna.plmography.services.ToggleReviewLikeService;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LikeController {
    private ToggleReviewLikeService toggleReviewLikeService;

    public LikeController(ToggleReviewLikeService toggleReviewLikeService) {
        this.toggleReviewLikeService = toggleReviewLikeService;
    }

    @PatchMapping("reviews/{id}/likeUserIds")
    public LikeUserIdsDto toggleLikeReview(
            @PathVariable Long id,
            @RequestAttribute Long userId
    ) {
        ReviewId reviewId = new ReviewId(id);
        LikeUserId likeUserId = new LikeUserId(userId);

        return toggleReviewLikeService.toggleLike(reviewId, likeUserId);
    }
}
