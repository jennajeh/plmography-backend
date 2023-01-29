package kr.jenna.plmography.dtos.review;

import java.util.List;

public class MyReviewsDto {
    private List<ReviewDto> myReviews;

    public MyReviewsDto(List<ReviewDto> myReviews) {
        this.myReviews = myReviews;
    }

    public List<ReviewDto> getMyReviews() {
        return myReviews;
    }
}
