package kr.jenna.plmography.models.VO;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Embeddable
@Getter
@NoArgsConstructor
public class ReviewBody {
    @Column(name = "review_body")
    private String value;

    public ReviewBody(String value) {
        this.value = value;
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
