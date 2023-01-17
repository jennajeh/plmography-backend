package kr.jenna.plmography.models;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import kr.jenna.plmography.dtos.Comment.CommentCreationDto;
import kr.jenna.plmography.dtos.Comment.CommentDto;
import kr.jenna.plmography.dtos.Comment.CommentModificationDto;
import kr.jenna.plmography.models.VO.CommentBody;
import kr.jenna.plmography.models.VO.PostId;
import kr.jenna.plmography.models.VO.UserId;
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

    public Comment() {
    }

    public Comment(Long id, UserId userId, PostId postId,
                   CommentBody commentBody, LocalDateTime createdAt) {
        this.id = id;
        this.userId = userId;
        this.postId = postId;
        this.commentBody = commentBody;
        this.isDeleted = false;
        this.createdAt = createdAt;
    }

    public Comment(UserId userId, PostId postId, CommentBody commentBody) {
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

    public static Comment fake() {
        return new Comment(1L, new UserId(1L), new PostId(1L), new CommentBody("reply"), LocalDateTime.now());
    }

    public CommentDto toCommentDto() {
        return new CommentDto(id, userId.getValue(), postId.getValue(), commentBody.getValue(),
                isDeleted, createdAt);
    }

    public void modify(CommentDto commentDto) {
        this.commentBody = new CommentBody(commentDto.getCommentBody());
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

    public CommentModificationDto commentModificationDto() {
        return new CommentModificationDto(id);
    }
}
