package kr.jenna.plmography.services.review;

import kr.jenna.plmography.dtos.page.PagesDto;
import kr.jenna.plmography.dtos.review.ReviewDto;
import kr.jenna.plmography.dtos.review.ReviewsDto;
import kr.jenna.plmography.dtos.user.WriterDto;
import kr.jenna.plmography.exceptions.UserNotFound;
import kr.jenna.plmography.models.Review;
import kr.jenna.plmography.models.User;
import kr.jenna.plmography.models.vo.LikeUserId;
import kr.jenna.plmography.models.vo.PostId;
import kr.jenna.plmography.models.vo.UserId;
import kr.jenna.plmography.repositories.ReviewCommentRepository;
import kr.jenna.plmography.repositories.ReviewRepository;
import kr.jenna.plmography.repositories.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class GetReviewsService {
    private final ReviewRepository reviewRepository;
    private final ReviewCommentRepository reviewCommentRepository;
    private final UserRepository userRepository;


    public GetReviewsService(ReviewRepository reviewRepository,
                             ReviewCommentRepository reviewCommentRepository,
                             UserRepository userRepository) {
        this.reviewRepository = reviewRepository;
        this.reviewCommentRepository = reviewCommentRepository;
        this.userRepository = userRepository;
    }

    public ReviewsDto reviewsWithNotLoggedIn(Integer page, Integer size) {
        Sort sort = Sort.by("createdAt").descending();

        Pageable pageable = PageRequest.of(page - 1, size, sort);

        Page<Review> reviews = reviewRepository.findAll(pageable);

        List<ReviewDto> reviewDtos = reviews.stream()
                .map(review -> {
                    User user = userRepository.findById(review.getUserId().getValue())
                            .orElseThrow(() -> new UserNotFound(review.getUserId().getValue()));

                    Long commentNumber = reviewCommentRepository.countByPostIdAndIsNotDeleted(new PostId(review.getId()));

                    return new ReviewDto(
                            review.getId(),
                            new WriterDto(
                                    user.getId(),
                                    user.getNickname().getValue(),
                                    user.getProfileImage().getValue()),
                            commentNumber,
                            review.getContentId().getValue(),
                            review.getStarRate(),
                            review.getReviewBody().getValue(),
                            review.getLikeUserIds()
                                    .stream()
                                    .map(LikeUserId::toDto)
                                    .collect(Collectors.toSet()),
                            review.getDeleted(),
                            review.getCreatedAt(),
                            review.getUpdatedAt());
                }).toList();

        PagesDto pagesDto = new PagesDto(reviews.getTotalPages());

        return new ReviewsDto(reviewDtos, pagesDto);
    }

    public ReviewsDto reviews(Long userId, Integer page, Integer size) {
        Sort sort = Sort.by("createdAt").descending();

        Pageable pageable = PageRequest.of(page - 1, size, sort);

        Page<Review> reviews = reviewRepository.findAllByUserIdNotLike(new UserId(userId), pageable);

        List<ReviewDto> reviewDtos = reviews.stream()
                .map(review -> {
                    User user = userRepository.findById(review.getUserId().getValue())
                            .orElseThrow(() -> new UserNotFound(review.getUserId().getValue()));

                    Long commentNumber = reviewCommentRepository.countByPostIdAndIsNotDeleted(new PostId(review.getId()));

                    return new ReviewDto(
                            review.getId(),
                            new WriterDto(
                                    user.getId(),
                                    user.getNickname().getValue(),
                                    user.getProfileImage().getValue()),
                            commentNumber,
                            review.getContentId().getValue(),
                            review.getStarRate(),
                            review.getReviewBody().getValue(),
                            review.getLikeUserIds()
                                    .stream()
                                    .map(LikeUserId::toDto)
                                    .collect(Collectors.toSet()),
                            review.getDeleted(),
                            review.getCreatedAt(),
                            review.getUpdatedAt());
                }).toList();

        PagesDto pagesDto = new PagesDto(reviews.getTotalPages());

        return new ReviewsDto(reviewDtos, pagesDto);
    }
}
