package kr.jenna.plmography.controllers;

import kr.jenna.plmography.dtos.postComment.MyPostCommentsDto;
import kr.jenna.plmography.dtos.postComment.PostCommentCreationDto;
import kr.jenna.plmography.dtos.postComment.PostCommentModificationRequestDto;
import kr.jenna.plmography.dtos.postComment.PostCommentModificationResponseDto;
import kr.jenna.plmography.dtos.postComment.PostCommentRegistrationDto;
import kr.jenna.plmography.dtos.postComment.PostCommentsDto;
import kr.jenna.plmography.exceptions.UserNotFound;
import kr.jenna.plmography.models.PostComment;
import kr.jenna.plmography.models.vo.PostCommentBody;
import kr.jenna.plmography.services.postComment.CreatePostCommentService;
import kr.jenna.plmography.services.postComment.DeletePostCommentService;
import kr.jenna.plmography.services.postComment.GetPostCommentsService;
import kr.jenna.plmography.services.postComment.PatchPostCommentService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PostCommentController {
    private CreatePostCommentService createPostCommentService;
    private GetPostCommentsService getPostCommentsService;
    private PatchPostCommentService patchPostCommentService;
    private DeletePostCommentService deletePostCommentService;

    public PostCommentController(CreatePostCommentService createPostCommentService,
                                 GetPostCommentsService getPostCommentsService,
                                 PatchPostCommentService patchPostCommentService,
                                 DeletePostCommentService deletePostCommentService) {
        this.createPostCommentService = createPostCommentService;
        this.getPostCommentsService = getPostCommentsService;
        this.patchPostCommentService = patchPostCommentService;
        this.deletePostCommentService = deletePostCommentService;
    }

    @PostMapping("/postComments")
    @ResponseStatus(HttpStatus.CREATED)
    public PostCommentCreationDto create(
            @RequestAttribute Long userId,
            @RequestBody PostCommentRegistrationDto postCommentRegistrationDto
    ) {
        PostComment postComment =
                createPostCommentService.create(userId, postCommentRegistrationDto);

        return postComment.toCreateDto();
    }

    @GetMapping("/posts/{postId}/postComments")
    public PostCommentsDto list(
            @PathVariable Long postId,
            @RequestParam(required = false, defaultValue = "1") Integer page,
            @RequestParam(required = false, defaultValue = "5") Integer size
    ) {
        return getPostCommentsService.list(postId, page, size);
    }

    @GetMapping("/postComments/me")
    public MyPostCommentsDto myComments(@RequestAttribute Long userId) {
        return getPostCommentsService.myComments(userId);
    }

    @PatchMapping("/postComments/{commentId}")
    public PostCommentModificationResponseDto modify(
            @RequestAttribute Long userId,
            @RequestBody PostCommentModificationRequestDto postCommentModificationRequestDto
    ) {
        Long commentId = postCommentModificationRequestDto.getId();
        PostCommentBody postCommentBody = new PostCommentBody(
                postCommentModificationRequestDto.getPostCommentBody());

        PostComment postComment =
                patchPostCommentService.modify(userId, commentId, postCommentBody);

        return postComment.toModificationDto();
    }

    @DeleteMapping("/postComments/{commentId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(
            @RequestAttribute Long userId,
            @PathVariable Long commentId
    ) {
        deletePostCommentService.delete(userId, commentId);
    }

    @ExceptionHandler(UserNotFound.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String userNotFound() {
        return "User not found!";
    }
}
