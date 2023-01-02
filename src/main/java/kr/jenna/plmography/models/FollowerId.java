package kr.jenna.plmography.models;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Embeddable
@Getter
@NoArgsConstructor
public class FollowerId {
    @Column(name = "follower_id")
    private Long value;

    public FollowerId(Long value) {
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

        FollowerId otherFollowerId = (FollowerId) other;

        return Objects.equals(value, otherFollowerId.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return "FollowerId{" +
                "value='" + value + '\'' +
                '}';
    }
}
