package kr.jenna.plmography.controllers;

import kr.jenna.plmography.dtos.like.LikeDto;
import kr.jenna.plmography.dtos.like.LikeUserIdsDto;
import kr.jenna.plmography.dtos.like.LikesDto;
import kr.jenna.plmography.dtos.like.SelectedLikeDto;
import kr.jenna.plmography.models.vo.LikeUserId;
import kr.jenna.plmography.models.vo.ReviewId;
import kr.jenna.plmography.services.like.CreateLikeService;
import kr.jenna.plmography.services.like.DeleteLikeService;
import kr.jenna.plmography.services.like.GetLikesService;
import kr.jenna.plmography.services.review.ToggleReviewLikeService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LikeController {
    private ToggleReviewLikeService toggleReviewLikeService;
    private GetLikesService getLikesService;
    private CreateLikeService createLikeService;
    private DeleteLikeService deleteLikeService;

    public LikeController(ToggleReviewLikeService toggleReviewLikeService,
                          GetLikesService getLikesService,
                          CreateLikeService createLikeService,
                          DeleteLikeService deleteLikeService) {
        this.toggleReviewLikeService = toggleReviewLikeService;
        this.getLikesService = getLikesService;
        this.createLikeService = createLikeService;
        this.deleteLikeService = deleteLikeService;
    }

    @PatchMapping("/reviews/{id}/likeUserIds")
    public LikeUserIdsDto toggleLikeReview(
            @PathVariable Long id,
            @RequestAttribute Long userId
    ) {
        ReviewId reviewId = new ReviewId(id);
        LikeUserId likeUserId = new LikeUserId(userId);

        return toggleReviewLikeService.toggleLike(reviewId, likeUserId);
    }

    @GetMapping("/likes")
    public LikesDto likes() {
        return getLikesService.likes();
    }

    @PostMapping("/likes")
    @ResponseStatus(HttpStatus.CREATED)
    public String like(@RequestBody LikeDto likeDto) {
        createLikeService.countLike(likeDto);

        return "ok";
    }

    @DeleteMapping("/likes")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@RequestBody SelectedLikeDto selectedLikeDto) {
        deleteLikeService.delete(selectedLikeDto);
    }
}
