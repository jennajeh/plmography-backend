package kr.jenna.plmography.controllers;

import kr.jenna.plmography.dtos.reviewComment.ReviewCommentCreationDto;
import kr.jenna.plmography.dtos.reviewComment.ReviewCommentRegistrationDto;
import kr.jenna.plmography.dtos.reviewComment.ReviewCommentsDto;
import kr.jenna.plmography.exceptions.ReviewCommentNotFound;
import kr.jenna.plmography.exceptions.UserNotFound;
import kr.jenna.plmography.models.ReviewComment;
import kr.jenna.plmography.services.reviewComment.CreateReviewCommentService;
import kr.jenna.plmography.services.reviewComment.DeleteReviewCommentService;
import kr.jenna.plmography.services.reviewComment.GetReviewCommentsService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/reviewComments")
public class ReviewCommentController {
    private CreateReviewCommentService createReviewCommentService;
    private GetReviewCommentsService getReviewCommentsService;
    private DeleteReviewCommentService deleteReviewCommentService;

    public ReviewCommentController(CreateReviewCommentService createReviewCommentService,
                                   GetReviewCommentsService getReviewCommentsService,
                                   DeleteReviewCommentService deleteReviewCommentService) {
        this.createReviewCommentService = createReviewCommentService;
        this.getReviewCommentsService = getReviewCommentsService;
        this.deleteReviewCommentService = deleteReviewCommentService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ReviewCommentCreationDto create(
            @RequestAttribute Long userId,
            @RequestBody ReviewCommentRegistrationDto commentRegistrationDto
    ) {
        ReviewComment reviewComment =
                createReviewCommentService.create(userId, commentRegistrationDto);

        return reviewComment.toCreateDto();
    }

    @GetMapping
    public ReviewCommentsDto list(
    ) {
        return getReviewCommentsService.list();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(
            @RequestAttribute Long userId,
            @PathVariable Long id
    ) {
        deleteReviewCommentService.delete(userId, id);
    }

    @ExceptionHandler(UserNotFound.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String userNotFound() {
        return "User not found!";
    }

    @ExceptionHandler(ReviewCommentNotFound.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String commentNotFound() {
        return "ReviewComment not found!";
    }
}
