package kr.jenna.plmography.models.vo;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import kr.jenna.plmography.exceptions.EmptyContent;

import java.util.Objects;

@Embeddable
public class ReviewCommentBody {
    @Column(name = "review_comment_body")
    private String value;

    public ReviewCommentBody() {
    }

    public ReviewCommentBody(String value) {
        if (value == null || value.equals("")) {
            throw new EmptyContent();
        }

        this.value = value;
    }

    public String getValue() {
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

        ReviewCommentBody otherReviewCommentBody = (ReviewCommentBody) other;

        return Objects.equals(value, otherReviewCommentBody.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
