package kr.jenna.plmography.models;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import kr.jenna.plmography.dtos.like.LikeDto;
import kr.jenna.plmography.models.vo.PostId;
import kr.jenna.plmography.models.vo.UserId;

@Entity
@Table(name = "Likes")
public class Like {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private PostId postId;

    @Embedded
    private UserId userId;

    public Like() {
    }

    public Like(Long id, PostId postId, UserId userId) {
        this.id = id;
        this.postId = postId;
        this.userId = userId;
    }

    public Like(PostId postId, UserId userId) {
        this.postId = postId;
        this.userId = userId;
    }

    public Long getId() {
        return id;
    }

    public PostId getPostId() {
        return postId;
    }

    public UserId getUserId() {
        return userId;
    }

    public LikeDto toDto() {
        return new LikeDto(id, postId.getValue(), userId.getValue());
    }

    public static Like fake() {
        return new Like(1L, new PostId(1L), new UserId(1L));
    }
}
