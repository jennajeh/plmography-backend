package kr.jenna.plmography.models;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import kr.jenna.plmography.models.vo.FollowingId;
import kr.jenna.plmography.models.vo.UserId;

@Entity
public class Subscribe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private UserId userId;

    @Embedded
    private FollowingId followingId;

    public Subscribe() {
    }

    public Subscribe(UserId userId, FollowingId followingId) {
        this.userId = userId;
        this.followingId = followingId;
    }

    public static Subscribe fake() {
        return new Subscribe(new UserId(1L), new FollowingId(1L));
    }

    public Long getId() {
        return id;
    }

    public UserId getUserId() {
        return userId;
    }

    public FollowingId getFollowingId() {
        return followingId;
    }
}
