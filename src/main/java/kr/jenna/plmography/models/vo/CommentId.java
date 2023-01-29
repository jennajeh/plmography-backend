package kr.jenna.plmography.models.vo;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.util.Objects;

@Embeddable
public class CommentId {
    @Column(name = "comment_id")
    private Long value;

    public CommentId() {
    }

    public CommentId(Long value) {
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

        CommentId otherCommentId = (CommentId) other;

        return Objects.equals(value, otherCommentId.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
