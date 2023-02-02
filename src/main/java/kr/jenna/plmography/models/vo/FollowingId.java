package kr.jenna.plmography.models.vo;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Embeddable
@Getter
@NoArgsConstructor
public class FollowingId {
    @Column(name = "friend_id")
    private Long value;

    public FollowingId(Long value) {
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

        FollowingId otherFollowingId = (FollowingId) other;

        return Objects.equals(value, otherFollowingId.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return "FollowingId{" +
                "value='" + value + '\'' +
                '}';
    }
}
