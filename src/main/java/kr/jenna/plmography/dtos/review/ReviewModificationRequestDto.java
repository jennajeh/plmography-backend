package kr.jenna.plmography.dtos.review;

public class ReviewModificationRequestDto {
    private Long id;
    private String reviewBody;

    public ReviewModificationRequestDto() {
    }

    public ReviewModificationRequestDto(Long id, String reviewBody) {
        this.id = id;
        this.reviewBody = reviewBody;
    }

    public Long getId() {
        return id;
    }

    public String getReviewBody() {
        return reviewBody;
    }
}
