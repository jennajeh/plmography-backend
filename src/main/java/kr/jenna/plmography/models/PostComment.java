package kr.jenna.plmography.models;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import kr.jenna.plmography.dtos.postComment.PostCommentCreationDto;
import kr.jenna.plmography.dtos.postComment.PostCommentModificationResponseDto;
import kr.jenna.plmography.models.vo.PostCommentBody;
import kr.jenna.plmography.models.vo.PostId;
import kr.jenna.plmography.models.vo.UserId;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
public class PostComment {
    @Id
    @GeneratedValue
    private Long id;

    @Embedded
    private UserId userId;

    @Embedded
    private PostId postId;

    @Embedded
    private PostCommentBody postCommentBody;

    private boolean isDeleted;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    public PostComment() {
    }

    public PostComment(Long id,
                       UserId userId,
                       PostId postId,
                       PostCommentBody postCommentBody) {
        this.id = id;
        this.userId = userId;
        this.postId = postId;
        this.postCommentBody = postCommentBody;
        this.isDeleted = false;
    }

    public PostComment(UserId userId,
                       PostId postId,
                       PostCommentBody postCommentBody) {
        this.userId = userId;
        this.postId = postId;
        this.postCommentBody = postCommentBody;
        this.isDeleted = false;
    }

    public Long getId() {
        return id;
    }

    public UserId getUserId() {
        return userId;
    }

    public PostId getPostId() {
        return postId;
    }

    public PostCommentBody getPostCommentBody() {
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

    public static PostComment fake() {
        return new PostComment(
                1L, new UserId(1L), new PostId(1L), new PostCommentBody("reply"));
    }

    public void modify(PostCommentBody postCommentBody) {
        this.postCommentBody = postCommentBody;
    }

    public void delete() {
        this.isDeleted = true;
    }

    public boolean isWriter(Long userId) {
        return this.userId.getValue() == userId;
    }

    public PostCommentCreationDto toCreateDto() {
        return new PostCommentCreationDto(id);
    }

    public PostCommentModificationResponseDto toModificationDto() {
        return new PostCommentModificationResponseDto(id);
    }
}
