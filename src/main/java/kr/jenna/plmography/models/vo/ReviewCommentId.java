package kr.jenna.plmography.models.vo;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.util.Objects;

@Embeddable
public class ReviewCommentId {
    @Column(name = "review_comment_id")
    private Long value;

    public ReviewCommentId() {
    }

    public ReviewCommentId(Long value) {
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

        ReviewCommentId otherReviewCommentId = (ReviewCommentId) other;

        return Objects.equals(value, otherReviewCommentId.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
