package kr.jenna.plmography.models.VO;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import kr.jenna.plmography.dtos.Like.LikeUserIdDto;

import java.util.Objects;

@Embeddable
public class ReviewId {
    @Column(name = "review_id")
    private Long value;

    public ReviewId() {
    }

    public ReviewId(Long value) {
        this.value = value;
    }

    public Long getValue() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }

        if (other == null || getClass() != other.getClass()) {
            return false;
        }

        ReviewId otherReviewId = (ReviewId) other;

        return Objects.equals(value, otherReviewId.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    public LikeUserIdDto toDto() {
        return new LikeUserIdDto(value);
    }

}
