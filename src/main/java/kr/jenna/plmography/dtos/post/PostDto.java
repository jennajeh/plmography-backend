package kr.jenna.plmography.dtos.post;

import kr.jenna.plmography.dtos.comment.CommentDto;
import kr.jenna.plmography.dtos.user.WriterDto;

import java.time.LocalDateTime;
import java.util.List;

public class PostDto {
    private Long id;
    private WriterDto writer;
    private List<CommentDto> comments;
    private String title;
    private String postBody;
    private Long hit;
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
            List<CommentDto> comments,
            String title,
            String postBody,
            Long hit,
            String image,
            boolean isDeleted, LocalDateTime createdAt,
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

    public List<CommentDto> getComments() {
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
                List.of(CommentDto.fake()),
                "title",
                "body",
                1L,
                "image",
                false,
                LocalDateTime.now().minusHours(1),
                LocalDateTime.now());
    }
}
