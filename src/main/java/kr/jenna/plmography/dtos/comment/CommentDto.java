package kr.jenna.plmography.dtos.comment;

import kr.jenna.plmography.dtos.user.WriterDto;

import java.time.LocalDateTime;

public class CommentDto {
    private Long id;
    private WriterDto writer;
    private Long postId;
    private String commentBody;
    private boolean isDeleted;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public CommentDto() {
    }

    public CommentDto(Long id,
                      WriterDto writer,
                      Long postId,
                      String commentBody,
                      boolean isDeleted,
                      LocalDateTime createdAt,
                      LocalDateTime updatedAt) {
        this.id = id;
        this.writer = writer;
        this.postId = postId;
        this.commentBody = commentBody;
        this.isDeleted = isDeleted;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public static CommentDto fake() {
        return new CommentDto(
                1L,
                new WriterDto(1L, "jenna", "https://source.boringavatars.com/beam/120/?nickname=jenna"),
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

    public String getCommentBody() {
        return commentBody;
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
