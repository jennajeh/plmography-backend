package kr.jenna.plmography.controllers;

import kr.jenna.plmography.dtos.CommentCreationDto;
import kr.jenna.plmography.dtos.CommentDto;
import kr.jenna.plmography.dtos.CommentRegistrationDto;
import kr.jenna.plmography.dtos.CommentsDto;
import kr.jenna.plmography.exceptions.CommentNotFound;
import kr.jenna.plmography.exceptions.UserNotFound;
import kr.jenna.plmography.models.Comment;
import kr.jenna.plmography.services.CreateCommentService;
import kr.jenna.plmography.services.DeleteCommentService;
import kr.jenna.plmography.services.GetCommentService;
import kr.jenna.plmography.services.GetCommentsService;
import kr.jenna.plmography.services.PatchCommentService;
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
@RequestMapping("/comments")
public class CommentController {
    private CreateCommentService createCommentService;
    private GetCommentsService getCommentsService;
    private GetCommentService getCommentService;
    private PatchCommentService patchCommentService;
    private DeleteCommentService deleteCommentService;

    public CommentController(CreateCommentService createCommentService,
                             GetCommentsService getCommentsService,
                             GetCommentService getCommentService,
                             PatchCommentService patchCommentService, DeleteCommentService deleteCommentService) {
        this.createCommentService = createCommentService;
        this.getCommentsService = getCommentsService;
        this.getCommentService = getCommentService;
        this.patchCommentService = patchCommentService;
        this.deleteCommentService = deleteCommentService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CommentCreationDto create(
            @RequestAttribute Long userId,
            @RequestBody CommentRegistrationDto commentRegistrationDto
    ) {
        Comment comment = createCommentService.create(userId, commentRegistrationDto);

        return comment.toCreateDto();
    }

    @GetMapping
    public CommentsDto list(
            @RequestAttribute Long userId,
            @RequestParam(required = false, defaultValue = "1") Integer page,
            @RequestParam(required = false, defaultValue = "1") Integer size
    ) {
        return getCommentsService.comments(userId, page, size);
    }

    @GetMapping("/{commentId}")
    public CommentDto detail(@PathVariable Long commentId) {
        return getCommentService.detail(commentId);
    }

    @PatchMapping("/{commentId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void patch(
            @RequestBody CommentDto commentDto,
            @PathVariable Long commentId
    ) {
        patchCommentService.update(commentId, commentDto.getCommentBody());
    }

    @DeleteMapping("/{commentId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long commentId) {
        deleteCommentService.delete(commentId);
    }

    @ExceptionHandler(UserNotFound.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String userNotFound() {
        return "User not found!";
    }

    @ExceptionHandler(CommentNotFound.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String commentNotFound() {
        return "Comment not found!";
    }
}
