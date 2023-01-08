package kr.jenna.plmography.dtos;

import java.time.LocalDateTime;

public class RecommentDto {
    private Long id;
    private Long parentId;
    private String recommentBody;
    private Long userId;
    private Long postId;
    private LocalDateTime createdAt;

    public RecommentDto() {
    }

    public RecommentDto(Long id, Long parentId, String recommentBody,
                        Long userId, Long postId, LocalDateTime createdAt) {
        this.id = id;
        this.parentId = parentId;
        this.recommentBody = recommentBody;
        this.userId = userId;
        this.postId = postId;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public Long getParentId() {
        return parentId;
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
