package kr.jenna.plmography.models;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import kr.jenna.plmography.dtos.Recomment.RecommentCreationDto;
import kr.jenna.plmography.dtos.Recomment.RecommentDto;
import kr.jenna.plmography.dtos.Recomment.RecommentModificationDto;
import kr.jenna.plmography.models.VO.CommentId;
import kr.jenna.plmography.models.VO.PostId;
import kr.jenna.plmography.models.VO.RecommentBody;
import kr.jenna.plmography.models.VO.UserId;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
public class Recomment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private CommentId commentId;

    @Embedded
    private RecommentBody recommentBody;

    @Embedded
    private UserId userId;

    @Embedded
    private PostId postId;

    @CreationTimestamp
    private LocalDateTime createdAt;

    public Recomment() {
    }

    public Recomment(Long id, CommentId commentId, RecommentBody recommentBody,
                     UserId userId, PostId postId, LocalDateTime createdAt) {
        this.id = id;
        this.commentId = commentId;
        this.recommentBody = recommentBody;
        this.userId = userId;
        this.postId = postId;
        this.createdAt = createdAt;
    }

    public Recomment(CommentId commentId, RecommentBody recommentBody,
                     UserId userId, PostId postId) {
        this.commentId = commentId;
        this.recommentBody = recommentBody;
        this.userId = userId;
        this.postId = postId;
    }

    public Long getId() {
        return id;
    }

    public CommentId getCommentId() {
        return commentId;
    }

    public RecommentBody getRecommentBody() {
        return recommentBody;
    }

    public UserId getUserId() {
        return userId;
    }

    public PostId getPostId() {
        return postId;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public static Recomment fake() {
        return new Recomment(
                1L,
                new CommentId(1L),
                new RecommentBody("대댓글"),
                new UserId(1L),
                new PostId(1L),
                LocalDateTime.now());
    }

    public RecommentDto toRecommentDto() {
        return new RecommentDto(
                id,
                commentId.getValue(),
                recommentBody.getValue(),
                userId.getValue(),
                postId.getValue(),
                createdAt);
    }

    public void modify(RecommentDto recommentDto) {
        this.recommentBody = new RecommentBody(recommentDto.getRecommentBody());
    }

    public RecommentCreationDto toCreateDto() {
        return new RecommentCreationDto(id);
    }

    public RecommentModificationDto toRecommentModificationDto() {
        return new RecommentModificationDto(id);
    }

    public boolean isWriter(Long userId) {
        return this.userId.getValue() == userId;
    }
}
