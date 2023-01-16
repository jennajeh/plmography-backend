package kr.jenna.plmography.models;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import kr.jenna.plmography.dtos.Review.ReviewCreationDto;
import kr.jenna.plmography.dtos.Review.ReviewModificationResponseDto;
import kr.jenna.plmography.models.VO.ContentId;
import kr.jenna.plmography.models.VO.LikeUserId;
import kr.jenna.plmography.models.VO.ReviewBody;
import kr.jenna.plmography.models.VO.UserId;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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
    private Set<LikeUserId> likeUserIds = new HashSet<>();

    private Boolean isDeleted;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @CreationTimestamp
    private LocalDateTime updatedAt;

    public Review() {
    }

    public Review(Set<LikeUserId> likeUserIds) {
        this.likeUserIds = likeUserIds;
    }

    public Review(Long id,
                  UserId userId,
                  ContentId contentId,
                  Long starRate,
                  ReviewBody reviewBody) {
        this.id = id;
        this.userId = userId;
        this.contentId = contentId;
        this.starRate = starRate;
        this.reviewBody = reviewBody;
        this.isDeleted = false;
    }

    public Review(UserId userId, ContentId contentId,
                  Long starRate, ReviewBody reviewBody) {
        this.userId = userId;
        this.contentId = contentId;
        this.starRate = starRate;
        this.reviewBody = reviewBody;
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
        return new Review(1L, new UserId(1L), new ContentId(1L),
                4L, new ReviewBody("영화가 재미있어요"));
    }

    public static Review fake(long id) {
        return new Review(id, new UserId(id), new ContentId(1L),
                4L, new ReviewBody("영화가 재미있어요 " + id));
    }

    public static List<Review> fakes(long count) {
        List<Review> reviews = new ArrayList<>();

        for (long i = 1; i <= count; i += 1) {
            Review review = Review.fake(i);

            reviews.add(review);
        }
        return reviews;
    }

    public void modify(ReviewBody reviewBody) {
        this.reviewBody = reviewBody;
    }

    public void delete() {
        this.isDeleted = true;
    }

    public boolean isWriter(Long userId) {
        return this.userId.getValue() == userId;
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

    public ReviewModificationResponseDto toReviewModificationDto() {
        return new ReviewModificationResponseDto(id);
    }
}
