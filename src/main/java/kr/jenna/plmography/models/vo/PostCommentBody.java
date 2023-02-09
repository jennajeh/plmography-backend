package kr.jenna.plmography.models.vo;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import kr.jenna.plmography.exceptions.EmptyContent;

import java.util.Objects;

@Embeddable
public class PostCommentBody {
    @Column(name = "post_comment_body")
    private String value;

    public PostCommentBody() {
    }

    public PostCommentBody(String value) {
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

        PostCommentBody otherPostCommentBody = (PostCommentBody) other;

        return Objects.equals(value, otherPostCommentBody.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
