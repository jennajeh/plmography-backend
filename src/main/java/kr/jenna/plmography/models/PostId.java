package kr.jenna.plmography.models;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.util.Objects;

@Embeddable
public class PostId {
    @Column(name = "post_id")
    private Long value;

    public PostId() {
    }

    public PostId(Long value) {
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

        PostId otherPostId = (PostId) other;
        
        return Objects.equals(value, otherPostId.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
