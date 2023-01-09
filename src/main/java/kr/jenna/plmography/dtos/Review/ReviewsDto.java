package kr.jenna.plmography.dtos.Review;

import kr.jenna.plmography.dtos.Page.PagesDto;

import java.util.List;

public class ReviewsDto {
    private List<ReviewDto> reviews;
    private PagesDto pages;

    public ReviewsDto() {
    }

    public ReviewsDto(List<ReviewDto> reviews, PagesDto pages) {
        this.reviews = reviews;
        this.pages = pages;
    }

    public List<ReviewDto> getReviews() {
        return reviews;
    }

    public PagesDto getPages() {
        return pages;
    }
}
