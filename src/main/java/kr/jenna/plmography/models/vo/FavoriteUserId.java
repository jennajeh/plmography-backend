package kr.jenna.plmography.models.vo;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.util.Objects;

@Embeddable
public class FavoriteUserId {
    @Column(name = "favorite_user_id")
    private Long value;

    public FavoriteUserId() {
    }

    public FavoriteUserId(Long value) {
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

        FavoriteUserId otherFavoriteUserId = (FavoriteUserId) other;

        return Objects.equals(value, otherFavoriteUserId.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return "FavoriteUserId{" +
                "value='" + value + '\'' +
                '}';
    }

    public FavoriteUserId toDto() {
        return new FavoriteUserId(value);
    }
}
