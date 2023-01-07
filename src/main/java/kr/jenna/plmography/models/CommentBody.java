package kr.jenna.plmography.models;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.util.Objects;

@Embeddable
public class CommentBody {
    @Column(name = "comment_body")
    private String value;

    public CommentBody() {
    }

    public CommentBody(String value) {
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

        CommentBody otherCommentBody = (CommentBody) other;

        return Objects.equals(value, otherCommentBody.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
