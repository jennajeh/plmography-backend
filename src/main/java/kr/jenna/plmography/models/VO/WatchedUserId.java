package kr.jenna.plmography.models.VO;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.util.Objects;

@Embeddable
public class WatchedUserId {
    @Column(name = "watched_user_id")
    private Long value;

    public WatchedUserId() {
    }

    public WatchedUserId(Long value) {
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

        WatchedUserId otherWatchedUserId = (WatchedUserId) other;

        return Objects.equals(value, otherWatchedUserId.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return "WatchedUserId{" +
                "value='" + value + '\'' +
                '}';
    }

    public WatchedUserId toDto() {
        return new WatchedUserId(value);
    }
}
