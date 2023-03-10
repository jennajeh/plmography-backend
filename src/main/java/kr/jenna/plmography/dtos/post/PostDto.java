package kr.jenna.plmography.dtos.post;

import com.sun.istack.Nullable;
import kr.jenna.plmography.dtos.postComment.PostCommentDto;
import kr.jenna.plmography.dtos.user.WriterDto;

import java.time.LocalDateTime;
import java.util.List;

public class PostDto {
    private Long id;

    private WriterDto writer;

    @Nullable
    private List<PostCommentDto> comments;

    private String title;

    @Nullable
    private String postBody;

    private Long hit;

    @Nullable
    private String image;

    private boolean isDeleted;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    public PostDto() {
    }

    public PostDto(
            Long id,
            WriterDto writer,
            String title,
            String postBody,
            Long hit,
            String image,
            boolean isDeleted,
            LocalDateTime createdAt,
            LocalDateTime updatedAt) {
        this.id = id;
        this.writer = writer;
        this.title = title;
        this.postBody = postBody;
        this.hit = hit;
        this.image = image;
        this.isDeleted = isDeleted;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public PostDto(
            Long id,
            WriterDto writer,
            List<PostCommentDto> comments,
            String title,
            String postBody,
            Long hit,
            String image,
            boolean isDeleted,
            LocalDateTime createdAt,
            LocalDateTime updatedAt) {
        this.id = id;
        this.writer = writer;
        this.comments = comments;
        this.title = title;
        this.postBody = postBody;
        this.hit = hit;
        this.image = image;
        this.isDeleted = isDeleted;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Long getId() {
        return id;
    }

    public WriterDto getWriter() {
        return writer;
    }

    public List<PostCommentDto> getComments() {
        return comments;
    }

    public String getTitle() {
        return title;
    }

    public String getPostBody() {
        return postBody;
    }

    public Long getHit() {
        return hit;
    }

    public String getImage() {
        return image;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public static PostDto fake() {
        return new PostDto(
                1L,
                new WriterDto(1L, "jenna", "image"),
                List.of(PostCommentDto.fake()),
                "title",
                "body",
                1L,
                "image",
                false,
                LocalDateTime.now().minusHours(1),
                LocalDateTime.now());
    }
}
