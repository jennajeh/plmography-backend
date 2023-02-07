package kr.jenna.plmography.dtos.postComment;

import kr.jenna.plmography.dtos.user.WriterDto;

import java.time.LocalDateTime;

public class PostCommentDto {
    private Long id;
    private WriterDto writer;
    private Long postId;
    private String postCommentBody;
    private boolean isDeleted;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public PostCommentDto() {
    }

    public PostCommentDto(Long id,
                          WriterDto writer,
                          Long postId,
                          String postCommentBody,
                          boolean isDeleted,
                          LocalDateTime createdAt,
                          LocalDateTime updatedAt) {
        this.id = id;
        this.writer = writer;
        this.postId = postId;
        this.postCommentBody = postCommentBody;
        this.isDeleted = isDeleted;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public static PostCommentDto fake() {
        return new PostCommentDto(
                1L,
                new WriterDto(
                        1L,
                        "jenna",
                        "https://source.boringavatars.com/beam/120/?nickname=jenna"),
                1L,
                "new reply",
                false,
                LocalDateTime.now(),
                LocalDateTime.now().plusDays(1));
    }

    public Long getId() {
        return id;
    }

    public WriterDto getWriter() {
        return writer;
    }

    public Long getPostId() {
        return postId;
    }

    public String getPostCommentBody() {
        return postCommentBody;
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
}
