package kr.jenna.plmography.models.vo;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import kr.jenna.plmography.dtos.wish.WishUserIdDto;

import java.util.Objects;

@Embeddable
public class WishUserId {
    @Column(name = "wish_user_id")
    private Long value;

    public WishUserId() {
    }

    public WishUserId(Long value) {
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

        WishUserId otherWishUserId = (WishUserId) other;

        return Objects.equals(value, otherWishUserId.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return "WishUserId{" +
                "value='" + value + '\'' +
                '}';
    }

    public WishUserIdDto toDto() {
        return new WishUserIdDto(value);
    }
}
