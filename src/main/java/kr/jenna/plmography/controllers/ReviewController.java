package kr.jenna.plmography.controllers;

import kr.jenna.plmography.dtos.review.MyReviewsDto;
import kr.jenna.plmography.dtos.review.ReviewCreationDto;
import kr.jenna.plmography.dtos.review.ReviewModificationRequestDto;
import kr.jenna.plmography.dtos.review.ReviewModificationResponseDto;
import kr.jenna.plmography.dtos.review.ReviewRegistrationDto;
import kr.jenna.plmography.dtos.review.ReviewsDto;
import kr.jenna.plmography.exceptions.InvalidUser;
import kr.jenna.plmography.exceptions.ReviewNotFound;
import kr.jenna.plmography.exceptions.UnmatchedPostId;
import kr.jenna.plmography.exceptions.UserNotFound;
import kr.jenna.plmography.models.Review;
import kr.jenna.plmography.models.vo.ReviewBody;
import kr.jenna.plmography.services.review.CreateReviewService;
import kr.jenna.plmography.services.review.DeleteReviewService;
import kr.jenna.plmography.services.review.GetReviewService;
import kr.jenna.plmography.services.review.GetReviewsService;
import kr.jenna.plmography.services.review.PatchReviewService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
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
@CrossOrigin
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
            @RequestParam(required = false, defaultValue = "5") Integer size
    ) {
        return getReviewsService.reviews(userId, page, size);
    }

    @GetMapping("/all")
    public ReviewsDto listWithNotLoggedIn(
            @RequestParam(required = false, defaultValue = "1") Integer page,
            @RequestParam(required = false, defaultValue = "5") Integer size
    ) {
        return getReviewsService.reviewsWithNotLoggedIn(page, size);
    }

    @GetMapping("/me")
    public MyReviewsDto myReview(@RequestAttribute Long userId) {
        return getReviewService.myReview(userId);
    }

    @PatchMapping("/{id}")
    public ReviewModificationResponseDto modify(
            @RequestAttribute Long userId,
            @RequestBody ReviewModificationRequestDto reviewModificationRequestDto
    ) {
        Long id = reviewModificationRequestDto.getId();

        Long starRate = reviewModificationRequestDto.getStarRate();
        ReviewBody reviewBody = new ReviewBody(reviewModificationRequestDto.getReviewBody());

        Review review = patchReviewService.modify(userId, id, starRate, reviewBody);

        return review.toReviewModificationDto();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(
            @RequestAttribute Long userId,
            @PathVariable Long id
    ) {
        deleteReviewService.delete(userId, id);
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

    @ExceptionHandler(UnmatchedPostId.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String unmatchedPostId() {
        return "Unmatched postId!";
    }
}
