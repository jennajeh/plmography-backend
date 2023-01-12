package kr.jenna.plmography.dtos.Review;

import kr.jenna.plmography.dtos.Like.LikeUserIdDto;
import kr.jenna.plmography.dtos.User.WriterDto;

import java.time.LocalDateTime;
import java.util.Set;

public class ReviewDto {
    private Long id;
    private WriterDto writer;
    private Long contentId;
    private Long starRate;
    private String reviewBody;
    private Set<LikeUserIdDto> likeUserIds;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public ReviewDto() {
    }

    public ReviewDto(Long id, WriterDto writer,
                     Long contentId, Long starRate,
                     String reviewBody, Set<LikeUserIdDto> likeUserIds,
                     LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.writer = writer;
        this.contentId = contentId;
        this.starRate = starRate;
        this.reviewBody = reviewBody;
        this.likeUserIds = likeUserIds;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Long getId() {
        return id;
    }

    public WriterDto getWriter() {
        return writer;
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

    public Set<LikeUserIdDto> getLikeUserIds() {
        return likeUserIds;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public static ReviewDto fake() {
        return new ReviewDto(
                1L,
                new WriterDto(1L, "jenna", "https://source.boringavatars.com/beam/120/?nickname=jenna"),
                1L, 3L, "강추!", Set.of(new LikeUserIdDto(2L)),
                LocalDateTime.now(), LocalDateTime.now());
    }
}