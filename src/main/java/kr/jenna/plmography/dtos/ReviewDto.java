package kr.jenna.plmography.dtos;

import java.util.Objects;

public class ReviewDto {
    private Long id;
    private Long userId;
    private Long contentId;
    private Long starRate;
    private String reviewBody;
    private String createdAt;

    public ReviewDto() {
    }

    public ReviewDto(Long id, Long userId, Long contentId,
                     Long starRate, String reviewBody, String createdAt) {
        this.id = id;
        this.userId = userId;
        this.contentId = contentId;
        this.starRate = starRate;
        this.reviewBody = reviewBody;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public Long getUserId() {
        return userId;
    }

    public Long getContentId() {
        return contentId;
    }

    public Long getStarRate() {
        return starRate;
    }

    public String getReviewBody() {
        return reviewBody;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    @Override
    public String toString() {
        return "ReviewDto{" +
                "id=" + id +
                ", userId=" + userId +
                ", contentId=" + contentId +
                ", starRate=" + starRate +
                ", reviewBody='" + reviewBody + '\'' +
                ", createdAt='" + createdAt + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object other) {
        ReviewDto otherReviewDto = (ReviewDto) other;
        return Objects.equals(id, otherReviewDto.id)
                && Objects.equals(userId, otherReviewDto.userId)
                && Objects.equals(contentId, otherReviewDto.contentId)
                && Objects.equals(starRate, otherReviewDto.starRate)
                && Objects.equals(reviewBody, otherReviewDto.reviewBody)
                && Objects.equals(createdAt, otherReviewDto.createdAt);
    }
}
