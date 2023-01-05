package kr.jenna.plmography.controllers;

import kr.jenna.plmography.dtos.ReviewCreationDto;
import kr.jenna.plmography.dtos.ReviewRegistrationDto;
import kr.jenna.plmography.models.Review;
import kr.jenna.plmography.services.CreateReviewService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/reviews")
public class ReviewController {

    private CreateReviewService createReviewService;

    public ReviewController(CreateReviewService createReviewService) {
        this.createReviewService = createReviewService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ReviewCreationDto create(
            @RequestBody ReviewRegistrationDto reviewRegistrationDto) {

        Review review = createReviewService.create(reviewRegistrationDto);

        return review.toCreateDto();
    }
}
