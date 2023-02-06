package kr.jenna.plmography.controllers;

import kr.jenna.plmography.dtos.post.MyPostsDto;
import kr.jenna.plmography.dtos.post.PostCreationDto;
import kr.jenna.plmography.dtos.post.PostModificationRequestDto;
import kr.jenna.plmography.dtos.post.PostModificationResponseDto;
import kr.jenna.plmography.dtos.post.PostRegistrationDto;
import kr.jenna.plmography.dtos.post.PostsDto;
import kr.jenna.plmography.dtos.post.SelectedPostsDto;
import kr.jenna.plmography.dtos.post.UpdateHitPostResponseDto;
import kr.jenna.plmography.exceptions.InvalidUser;
import kr.jenna.plmography.exceptions.UserNotFound;
import kr.jenna.plmography.models.Post;
import kr.jenna.plmography.models.vo.Image;
import kr.jenna.plmography.models.vo.PostBody;
import kr.jenna.plmography.models.vo.Title;
import kr.jenna.plmography.services.post.CreatePostService;
import kr.jenna.plmography.services.post.DeletePostService;
import kr.jenna.plmography.services.post.GetPostService;
import kr.jenna.plmography.services.post.GetPostsService;
import kr.jenna.plmography.services.post.PatchPostService;
import kr.jenna.plmography.utils.S3Uploader;
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
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/posts")
@CrossOrigin
public class PostController {
    private final S3Uploader s3Uploader;
    private CreatePostService createPostService;
    private GetPostsService getPostsService;
    private GetPostService getPostService;
    private PatchPostService patchPostService;
    private DeletePostService deletePostService;

    public PostController(S3Uploader s3Uploader,
                          CreatePostService createPostService,
                          GetPostsService getPostsService,
                          GetPostService getPostService,
                          PatchPostService patchPostService,
                          DeletePostService deletePostService) {
        this.s3Uploader = s3Uploader;
        this.createPostService = createPostService;
        this.getPostsService = getPostsService;
        this.getPostService = getPostService;
        this.patchPostService = patchPostService;
        this.deletePostService = deletePostService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PostCreationDto create(
            @RequestAttribute Long userId,
            @RequestBody PostRegistrationDto postRegistrationDto
    ) {
        Post post = createPostService.create(userId, postRegistrationDto);

        return post.toPostCreationDto();
    }

    @GetMapping("/topHit")
    public PostsDto top3Hit() {
        return getPostsService.top3Hit();
    }

    @GetMapping
    public PostsDto list(
            @RequestParam(required = false, defaultValue = "") String keyword,
            @RequestParam(required = false, defaultValue = "1") Integer page,
            @RequestParam(required = false, defaultValue = "10") Integer size
    ) {
        return getPostsService.list(keyword, page, size);
    }

    @GetMapping("/me")
    public MyPostsDto myPost(@RequestAttribute Long userId) {
        return getPostService.myPost(userId);
    }

    @PatchMapping("/{postId}/updateHit")
    public UpdateHitPostResponseDto updateHit(@PathVariable Long postId) {
        return patchPostService.updateHit(postId);
    }

    @PatchMapping("/{postId}/modify")
    public PostModificationResponseDto modify(
            @RequestAttribute Long userId,
            @RequestBody PostModificationRequestDto postModificationRequestDto
    ) {
        Long postId = postModificationRequestDto.getPostId();
        Title title = new Title(postModificationRequestDto.getTitle());
        PostBody postBody = new PostBody(postModificationRequestDto.getPostBody());
        Image image = new Image(postModificationRequestDto.getImage());

        return patchPostService.modify(userId, postId, title, postBody, image);
    }

    @DeleteMapping("/{postId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(
            @RequestAttribute Long userId,
            @PathVariable Long postId
    ) {
        deletePostService.delete(userId, postId);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePosts(@RequestBody SelectedPostsDto selectedPostsDto) {
        deletePostService.deletePosts(selectedPostsDto);
    }

    @PostMapping("/upload")
    public String upload(MultipartFile multipartFile) throws IOException {
        return s3Uploader.uploadFiles(multipartFile, "plmographycommunity");
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
}
