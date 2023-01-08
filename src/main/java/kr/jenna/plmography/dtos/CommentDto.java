package kr.jenna.plmography.dtos;

import java.time.LocalDateTime;
import java.util.Objects;

public class CommentDto {
    private Long id;
    private Long userId;
    private Long postId;
    private String commentBody;
    private boolean isDeleted;
    private LocalDateTime createdAt;

    public CommentDto() {
    }

    public CommentDto(Long id, Long userId, Long postId,
                      String commentBody, boolean isDeleted, LocalDateTime createdAt) {
        this.id = id;
        this.userId = userId;
        this.postId = postId;
        this.commentBody = commentBody;
        this.isDeleted = isDeleted;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public Long getUserId() {
        return userId;
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

    @Override
    public String toString() {
        return "CommentDto{" +
                "id=" + id +
                ", userId=" + userId +
                ", postId=" + postId +
                ", commentBody='" + commentBody + '\'' +
                ", isDeleted=" + isDeleted +
                ", createdAt='" + createdAt + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object other) {
        CommentDto that = (CommentDto) other;
        return isDeleted == that.isDeleted
                && Objects.equals(id, that.id)
                && Objects.equals(userId, that.userId)
                && Objects.equals(postId, that.postId)
                && Objects.equals(commentBody, that.commentBody)
                && Objects.equals(createdAt, that.createdAt);
    }
}
