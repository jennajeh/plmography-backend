package kr.jenna.plmography.dtos.review;

public class ReviewRegistrationDto {
    private Long contentId;
    private Long starRate;
    private String reviewBody;

    public ReviewRegistrationDto(Long contentId, Long starRate, String reviewBody) {
        this.contentId = contentId;
        this.starRate = starRate;
        this.reviewBody = reviewBody;
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
