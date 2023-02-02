package kr.jenna.plmography.dtos.review;

public class ReviewModificationRequestDto {
    private Long id;
    private Long starRate;
    private String reviewBody;

    public ReviewModificationRequestDto() {
    }

    public ReviewModificationRequestDto(Long id, Long starRate, String reviewBody) {
        this.id = id;
        this.starRate = starRate;
        this.reviewBody = reviewBody;
    }

    public Long getId() {
        return id;
    }

    public Long getStarRate() {
        return starRate;
    }

    public String getReviewBody() {
        return reviewBody;
    }
}
