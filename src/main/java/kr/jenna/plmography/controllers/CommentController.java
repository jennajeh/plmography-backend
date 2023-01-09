package kr.jenna.plmography.controllers;

import kr.jenna.plmography.dtos.Comment.CommentCreationDto;
import kr.jenna.plmography.dtos.Comment.CommentDto;
import kr.jenna.plmography.dtos.Comment.CommentModificationDto;
import kr.jenna.plmography.dtos.Comment.CommentRegistrationDto;
import kr.jenna.plmography.dtos.Comment.CommentsDto;
import kr.jenna.plmography.exceptions.CommentNotFound;
import kr.jenna.plmography.exceptions.UserNotFound;
import kr.jenna.plmography.models.Comment;
import kr.jenna.plmography.models.VO.CommentId;
import kr.jenna.plmography.services.Comment.CreateCommentService;
import kr.jenna.plmography.services.Comment.DeleteCommentService;
import kr.jenna.plmography.services.Comment.GetCommentService;
import kr.jenna.plmography.services.Comment.GetCommentsService;
import kr.jenna.plmography.services.Comment.PatchCommentService;
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
            @RequestParam(required = false, defaultValue = "5") Integer size
    ) {
        return getCommentsService.comments(userId, page, size);
    }

    @GetMapping("/{id}")
    public CommentDto detail(@PathVariable Long id) {
        return getCommentService.detail(id);
    }

    @PatchMapping("/{id}")
    public CommentModificationDto patch(
            @RequestAttribute Long userId,
            @PathVariable Long id,
            @RequestBody CommentDto commentDto
    ) {
        CommentId commentId = new CommentId(id);

        Comment comment = patchCommentService.update(userId, commentId, commentDto);

        return comment.commentModificationDto();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(
            @RequestAttribute Long userId,
            @PathVariable Long id
    ) {
        CommentId commentId = new CommentId(id);
        deleteCommentService.delete(userId, commentId);
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
