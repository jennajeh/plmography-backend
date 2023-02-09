package kr.jenna.plmography.models;

import kr.jenna.plmography.dtos.reviewComment.ReviewCommentCreationDto;
import kr.jenna.plmography.dtos.reviewComment.ReviewCommentDto;
import kr.jenna.plmography.models.vo.PostId;
import kr.jenna.plmography.models.vo.ReviewCommentBody;
import kr.jenna.plmography.models.vo.UserId;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ReviewCommentTest {

    @Test
    void creation() {
        ReviewComment reviewComment = new ReviewComment(
                1L, new UserId(1L), new PostId(1L), new ReviewCommentBody("reply"));

        assertThat(reviewComment.getReviewCommentBody())
                .isEqualTo(new ReviewCommentBody("reply"));
    }

    @Test
    void modify() {
        ReviewComment reviewComment = ReviewComment.fake();

        ReviewCommentDto reviewCommentDto = ReviewCommentDto.fake();

        reviewComment.modify(new ReviewCommentBody(reviewCommentDto.getReviewCommentBody()));

        assertThat(reviewComment.getReviewCommentBody().getValue())
                .isEqualTo(reviewCommentDto.getReviewCommentBody());
    }

    @Test
    void delete() {
        ReviewComment reviewComment = ReviewComment.fake();

        reviewComment.delete();

        assertThat(reviewComment.isDeleted()).isTrue();
    }

    @Test
    void toCreationDto() {
        ReviewComment reviewComment = ReviewComment.fake();

        ReviewCommentCreationDto reviewCommentCreationDto = reviewComment.toCreateDto();

        assertThat(reviewCommentCreationDto).isNotNull();
        assertThat(reviewCommentCreationDto.getId()).isEqualTo(reviewComment.getId());
    }

    @Test
    void isWriter() {
        ReviewComment reviewComment = ReviewComment.fake();

        assertThat(reviewComment.isWriter(1L)).isTrue();
        assertThat(reviewComment.isWriter(2L)).isFalse();
    }
}
