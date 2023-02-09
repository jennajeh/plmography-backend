package kr.jenna.plmography.models.vo;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.util.Objects;

@Embeddable
public class PostBody {
    @Column(name = "post_body", length = 10000)
    private String value;

    public PostBody() {
    }

    public PostBody(String value) {
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

        PostBody otherPostBody = (PostBody) other;

        return Objects.equals(value, otherPostBody.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return "PostBody {" +
                "value='" + value + '\'' +
                '}';
    }
}
