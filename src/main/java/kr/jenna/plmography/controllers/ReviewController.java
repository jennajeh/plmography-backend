package kr.jenna.plmography.controllers;

import kr.jenna.plmography.dtos.ReviewCreationDto;
import kr.jenna.plmography.dtos.ReviewDto;
import kr.jenna.plmography.dtos.ReviewRegistrationDto;
import kr.jenna.plmography.dtos.ReviewsDto;
import kr.jenna.plmography.exceptions.ReviewNotFound;
import kr.jenna.plmography.exceptions.UserNotFound;
import kr.jenna.plmography.models.Review;
import kr.jenna.plmography.services.CreateReviewService;
import kr.jenna.plmography.services.DeleteReviewService;
import kr.jenna.plmography.services.GetReviewService;
import kr.jenna.plmography.services.GetReviewsService;
import kr.jenna.plmography.services.PatchReviewService;
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

    @GetMapping("/{reviewId}")
    public ReviewDto detail(@PathVariable Long reviewId) {
        return getReviewService.detail(reviewId);
    }

    @PatchMapping("/{reviewId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void patch(
            @RequestBody ReviewRegistrationDto reviewRegistrationDto,
            @PathVariable Long reviewId
    ) {
        patchReviewService.update(reviewRegistrationDto.getReviewBody(), reviewId);
    }

    @DeleteMapping("/{reviewId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long reviewId) {
        deleteReviewService.delete(reviewId);
    }

    @ExceptionHandler(UserNotFound.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String userNotFound() {
        return "User not found!";
    }

    @ExceptionHandler(ReviewNotFound.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String reviewNotFound() {
        return "Review not found!";
    }
}
