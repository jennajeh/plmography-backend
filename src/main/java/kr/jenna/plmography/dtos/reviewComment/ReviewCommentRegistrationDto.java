package kr.jenna.plmography.dtos.reviewComment;

public class ReviewCommentRegistrationDto {
    private Long userId;
    private Long postId;
    private String reviewCommentBody;

    public ReviewCommentRegistrationDto() {
    }

    public ReviewCommentRegistrationDto(Long userId, Long postId, String reviewCommentBody) {
        this.userId = userId;
        this.postId = postId;
        this.reviewCommentBody = reviewCommentBody;
    }

    public Long getUserId() {
        return userId;
    }

    public Long getPostId() {
        return postId;
    }

    public String getReviewCommentBody() {
        return reviewCommentBody;
    }
}
