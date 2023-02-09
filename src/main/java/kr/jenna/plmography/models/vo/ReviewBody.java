package kr.jenna.plmography.models.vo;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import kr.jenna.plmography.exceptions.EmptyContent;

import java.util.Objects;

@Embeddable
public class ReviewBody {
    @Column(name = "review_body")
    private String value;

    public ReviewBody() {
    }

    public ReviewBody(String value) {
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

        ReviewBody otherReviewBody = (ReviewBody) other;

        return Objects.equals(value, otherReviewBody.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return "ReviewBody {" +
                "value='" + value + '\'' +
                '}';
    }
}
