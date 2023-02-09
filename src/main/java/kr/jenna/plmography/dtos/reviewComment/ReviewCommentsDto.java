package kr.jenna.plmography.dtos.reviewComment;

import java.util.List;

public class ReviewCommentsDto {
    private List<ReviewCommentDto> reviewComments;

    public ReviewCommentsDto(List<ReviewCommentDto> reviewComments) {
        this.reviewComments = reviewComments;
    }

    public List<ReviewCommentDto> getReviewComments() {
        return reviewComments;
    }
}
