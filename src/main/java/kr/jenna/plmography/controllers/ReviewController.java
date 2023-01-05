package kr.jenna.plmography.controllers;

import kr.jenna.plmography.dtos.ReviewCreationDto;
import kr.jenna.plmography.dtos.ReviewDto;
import kr.jenna.plmography.dtos.ReviewRegistrationDto;
import kr.jenna.plmography.exceptions.ReviewNotFound;
import kr.jenna.plmography.exceptions.UserNotFound;
import kr.jenna.plmography.models.Review;
import kr.jenna.plmography.services.CreateReviewService;
import kr.jenna.plmography.services.GetReviewService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/reviews")
public class ReviewController {

    private CreateReviewService createReviewService;
    private GetReviewService getReviewService;

    public ReviewController(CreateReviewService createReviewService,
                            GetReviewService getReviewService) {
        this.createReviewService = createReviewService;
        this.getReviewService = getReviewService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ReviewCreationDto create(
            @RequestBody ReviewRegistrationDto reviewRegistrationDto) {

        Review review = createReviewService.create(reviewRegistrationDto);

        return review.toCreateDto();
    }

    @GetMapping("/{userId}")
    public ReviewDto detail(@PathVariable Long userId) {
        Review review = getReviewService.detail(userId);

        return review.toReviewDto();
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
