package kr.jenna.plmography.models.vo;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import kr.jenna.plmography.dtos.like.LikeUserIdDto;

import java.util.Objects;

@Embeddable
public class LikeUserId {
    @Column(name = "like_user_id")
    private Long value;

    public LikeUserId() {
    }

    public LikeUserId(Long value) {
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

        LikeUserId otherLikeUserId = (LikeUserId) other;

        return Objects.equals(value, otherLikeUserId.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return "LikeUserId{" +
                "value='" + value + '\'' +
                '}';
    }

    public LikeUserIdDto toDto() {
        return new LikeUserIdDto(value);
    }
}
