package kr.jenna.plmography.models;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import kr.jenna.plmography.dtos.ReviewCreationDto;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Review {
    @Id
    @GeneratedValue
    private Long id;

    @Embedded
    private UserId userId;

    @Embedded
    private ContentId contentId;

    private Long starRate;

    @Embedded
    private ReviewBody reviewBody;

    @ElementCollection
    private Set<LikeUserId> likeUserIds;

    private Boolean isDeleted;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @CreationTimestamp
    private LocalDateTime updatedAt;

    public Review() {
    }


    public Review(UserId userId, ContentId contentId,
                  Long starRate, ReviewBody reviewBody) {
        this.userId = userId;
        this.contentId = contentId;
        this.starRate = starRate;
        this.reviewBody = reviewBody;
        this.likeUserIds = new HashSet<>();
        this.isDeleted = false;
    }

    public Long getId() {
        return id;
    }

    public UserId getUserId() {
        return userId;
    }

    public ContentId getContentId() {
        return contentId;
    }

    public Long getStarRate() {
        return starRate;
    }

    public ReviewBody getReviewBody() {
        return reviewBody;
    }

    public Set<LikeUserId> getLikeUserIds() {
        return likeUserIds;
    }

    public Boolean getDeleted() {
        return isDeleted;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public static Review fake() {
        return new Review(new UserId(1L), new ContentId(1L),
                4L, new ReviewBody("영화가 재미있어요"));
    }

    public void update(ReviewBody reviewBody) {
        this.reviewBody = reviewBody;
    }

    public void delete() {
        this.isDeleted = true;
    }

    public void toggleLike(LikeUserId likeUserId) {
        if (likeUserIds.contains(likeUserId)) {
            likeUserIds.remove(likeUserId);

            return;
        }

        likeUserIds.add(likeUserId);
    }

    public ReviewCreationDto toCreateDto() {
        return new ReviewCreationDto(id);
    }
}
