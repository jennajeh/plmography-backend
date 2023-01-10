package kr.jenna.plmography.controllers;

import kr.jenna.plmography.dtos.Review.ReviewCreationDto;
import kr.jenna.plmography.dtos.Review.ReviewDto;
import kr.jenna.plmography.dtos.Review.ReviewModificationDto;
import kr.jenna.plmography.dtos.Review.ReviewRegistrationDto;
import kr.jenna.plmography.dtos.Review.ReviewsDto;
import kr.jenna.plmography.exceptions.InvalidUser;
import kr.jenna.plmography.exceptions.ReviewNotFound;
import kr.jenna.plmography.exceptions.UnmatchedPostId;
import kr.jenna.plmography.exceptions.UnmodifiableReview;
import kr.jenna.plmography.exceptions.UserNotFound;
import kr.jenna.plmography.models.Review;
import kr.jenna.plmography.models.VO.PostId;
import kr.jenna.plmography.services.Review.CreateReviewService;
import kr.jenna.plmography.services.Review.DeleteReviewService;
import kr.jenna.plmography.services.Review.GetReviewService;
import kr.jenna.plmography.services.Review.GetReviewsService;
import kr.jenna.plmography.services.Review.PatchReviewService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/reviews")
public class ReviewController {
    private CreateReviewService createReviewService;
    private GetReviewService getReviewService;
    private PatchReviewService patchReviewService;
    private GetReviewsService getReviewsService;
    private DeleteReviewService deleteReviewService;

    public ReviewController(CreateReviewService createReviewService,
                            GetReviewService getReviewService,
                            PatchReviewService patchReviewService,
                            GetReviewsService getReviewsService,
                            DeleteReviewService deleteReviewService) {
        this.createReviewService = createReviewService;
        this.getReviewService = getReviewService;
        this.patchReviewService = patchReviewService;
        this.getReviewsService = getReviewsService;
        this.deleteReviewService = deleteReviewService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ReviewCreationDto create(
            @RequestAttribute Long userId,
            @RequestBody ReviewRegistrationDto reviewRegistrationDto) {

        Review review = createReviewService.create(userId, reviewRegistrationDto);

        return review.toCreateDto();
    }

    @GetMapping
    public ReviewsDto list(
            @RequestAttribute Long userId,
            @RequestParam(required = false, defaultValue = "1") Integer page,
            @RequestParam(required = false, defaultValue = "3") Integer size
    ) {
        return getReviewsService.reviews(userId, page, size);
    }

    @GetMapping("/{id}")
    public ReviewDto detail(@PathVariable Long id) {
        return getReviewService.detail(id);
    }

    @PatchMapping("/{id}")
    public ReviewModificationDto modify(
            @RequestAttribute Long userId,
            @PathVariable Long id,
            @RequestBody ReviewDto reviewDto
    ) {
        PostId postId = new PostId(id);

        Review review = patchReviewService.modify(userId, postId, reviewDto);

        return review.toReviewModificationDto();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(
            @RequestAttribute Long userId,
            @PathVariable Long id
    ) {
        PostId postId = new PostId(id);

        deleteReviewService.delete(userId, postId);
    }

    @ExceptionHandler(UserNotFound.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String userNotFound() {
        return "User not found!";
    }

    @ExceptionHandler(InvalidUser.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String InvalidUser() {
        return "Invalid user!";
    }

    @ExceptionHandler(ReviewNotFound.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String reviewNotFound() {
        return "Review not found!";
    }

    @ExceptionHandler(UnmodifiableReview.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String unmodifiableReview() {
        return "Unmodifiable review!";
    }

    @ExceptionHandler(UnmatchedPostId.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String unmatchedPostId() {
        return "Unmatched postId!";
    }
}
