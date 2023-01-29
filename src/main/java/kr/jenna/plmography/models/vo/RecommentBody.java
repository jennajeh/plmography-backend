package kr.jenna.plmography.models.vo;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.util.Objects;

@Embeddable
public class RecommentBody {
    @Column(name = "recomment_body")
    private String value;

    public RecommentBody() {
    }

    public RecommentBody(String value) {
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

        RecommentBody otherRecommentBody = (RecommentBody) other;

        return Objects.equals(value, otherRecommentBody.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
