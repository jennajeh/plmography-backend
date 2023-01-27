package kr.jenna.plmography.controllers;

import kr.jenna.plmography.dtos.Comment.CommentCreationDto;
import kr.jenna.plmography.dtos.Comment.CommentModificationRequestDto;
import kr.jenna.plmography.dtos.Comment.CommentModificationResponseDto;
import kr.jenna.plmography.dtos.Comment.CommentRegistrationDto;
import kr.jenna.plmography.dtos.Comment.CommentsDto;
import kr.jenna.plmography.exceptions.CommentNotFound;
import kr.jenna.plmography.exceptions.UserNotFound;
import kr.jenna.plmography.models.Comment;
import kr.jenna.plmography.models.VO.CommentBody;
import kr.jenna.plmography.services.Comment.CreateCommentService;
import kr.jenna.plmography.services.Comment.DeleteCommentService;
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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/comments")
public class CommentController {
    private CreateCommentService createCommentService;
    private GetCommentsService getCommentsService;
    private PatchCommentService patchCommentService;
    private DeleteCommentService deleteCommentService;

    public CommentController(CreateCommentService createCommentService,
                             GetCommentsService getCommentsService,
                             PatchCommentService patchCommentService, DeleteCommentService deleteCommentService) {
        this.createCommentService = createCommentService;
        this.getCommentsService = getCommentsService;
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
            @RequestAttribute Long userId
    ) {
        return getCommentsService.comments(userId);
    }

    @GetMapping("/all")
    public CommentsDto listWithNotLoggedIn(
    ) {
        return getCommentsService.commentsWithNotLoggedIn();
    }

    @PatchMapping("/{id}")
    public CommentModificationResponseDto modify(
            @RequestAttribute Long userId,
            @RequestBody CommentModificationRequestDto commentModificationRequestDto
    ) {
        Long id = commentModificationRequestDto.getId();

        CommentBody commentBody = new CommentBody(commentModificationRequestDto.getCommentBody());

        Comment comment = patchCommentService.modify(userId, id, commentBody);

        return comment.commentModificationDto();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(
            @RequestAttribute Long userId,
            @PathVariable Long id
    ) {
        deleteCommentService.delete(userId, id);
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
