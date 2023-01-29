package kr.jenna.plmography.dtos.Recomment;

import java.time.LocalDateTime;

public class RecommentDto {
    private Long id;
    private Long commentId;
    private String recommentBody;
    private Long userId;
    private Long postId;
    private LocalDateTime createdAt;

    public RecommentDto() {
    }

    public RecommentDto(Long id, Long commentId, String recommentBody,
                        Long userId, Long postId, LocalDateTime createdAt) {
        this.id = id;
        this.commentId = commentId;
        this.recommentBody = recommentBody;
        this.userId = userId;
        this.postId = postId;
        this.createdAt = createdAt;
    }

    public static RecommentDto fake() {
        return new RecommentDto(1L, 1L, "정말 추천합니다~", 1L, 1L, LocalDateTime.now());
    }

    public Long getId() {
        return id;
    }

    public Long getCommentId() {
        return commentId;
    }

    public String getRecommentBody() {
        return recommentBody;
    }

    public Long getUserId() {
        return userId;
    }

    public Long getPostId() {
        return postId;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
