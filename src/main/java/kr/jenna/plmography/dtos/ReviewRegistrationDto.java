package kr.jenna.plmography.dtos;

public class ReviewRegistrationDto {
    private Long id;
    private Long userId;
    private Long contentId;
    private Long starRate;
    private String reviewBody;

    public ReviewRegistrationDto(Long id, Long userId, Long contentId,
                                 Long starRate, String reviewBody) {
        this.id = id;
        this.userId = userId;
        this.contentId = contentId;
        this.starRate = starRate;
        this.reviewBody = reviewBody;
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
}
