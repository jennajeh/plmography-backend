package kr.jenna.plmography.models;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import kr.jenna.plmography.dtos.comment.CommentCreationDto;
import kr.jenna.plmography.dtos.comment.CommentModificationResponseDto;
import kr.jenna.plmography.models.vo.CommentBody;
import kr.jenna.plmography.models.vo.PostId;
import kr.jenna.plmography.models.vo.UserId;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private UserId userId;

    @Embedded
    private PostId postId;

    @Embedded
    private CommentBody commentBody;

    private boolean isDeleted;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @CreationTimestamp
    private LocalDateTime updatedAt;

    public Comment() {
    }

    public Comment(Long id,
                   UserId userId,
                   PostId postId,
                   CommentBody commentBody) {
        this.id = id;
        this.userId = userId;
        this.postId = postId;
        this.commentBody = commentBody;
        this.isDeleted = false;
    }

    public Comment(UserId userId,
                   PostId postId,
                   CommentBody commentBody) {
        this.userId = userId;
        this.postId = postId;
        this.commentBody = commentBody;
        this.isDeleted = false;
    }

    public Long getId() {
        return id;
    }

    public UserId getUserId() {
        return userId;
    }

    public CommentBody getCommentBody() {
        return commentBody;
    }

    public PostId getPostId() {
        return postId;
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

    public static Comment fake() {
        return new Comment(1L, new UserId(1L), new PostId(1L), new CommentBody("reply"));
    }

    public void modify(CommentBody commentBody) {
        this.commentBody = commentBody;
    }

    public void delete() {
        this.isDeleted = true;
    }

    public boolean isWriter(Long userId) {
        return this.userId.getValue() == userId;
    }

    public CommentCreationDto toCreateDto() {
        return new CommentCreationDto(id);
    }

    public CommentModificationResponseDto commentModificationDto() {
        return new CommentModificationResponseDto(id);
    }
}
