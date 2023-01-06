package kr.jenna.plmography.services;

import kr.jenna.plmography.dtos.PagesDto;
import kr.jenna.plmography.dtos.ReviewDto;
import kr.jenna.plmography.dtos.ReviewsDto;
import kr.jenna.plmography.models.Review;
import kr.jenna.plmography.repositories.ReviewRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class GetReviewsService {
    private final ReviewRepository reviewRepository;

    public GetReviewsService(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    public ReviewsDto reviews(Long userId, Integer page, Integer size) {
        Sort sort = Sort.by("createdAt").descending();

        Pageable pageable = PageRequest.of(page - 1, size, sort);

        Page<Review> reviews = reviewRepository.findAllByUserId(userId, pageable);

        List<ReviewDto> reviewDtos = reviews.stream()
                .map(review -> new ReviewDto(
                        review.getId(), review.getUserId().getValue(),
                        review.getContentId().getValue(), review.getStarRate(),
                        review.getReviewBody().getValue(), review.getCreatedAt()
                        .format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))))
                .collect(Collectors.toList());

        PagesDto pagesDto = new PagesDto(reviews.getTotalPages());

        return new ReviewsDto(reviewDtos, pagesDto);
    }
}
