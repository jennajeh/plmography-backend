package kr.jenna.plmography.models.vo;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import kr.jenna.plmography.dtos.wish.WishContentIdDto;

import java.util.Objects;

@Embeddable
public class WishContentId {
    @Column(name = "wish_content_id")
    private String value;

    public WishContentId() {
    }

    public WishContentId(String value) {
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

        WishContentId otherWishContentId = (WishContentId) other;

        return Objects.equals(value, otherWishContentId.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return "WishContentId{" +
                "value='" + value + '\'' +
                '}';
    }

    public WishContentIdDto toDto() {
        return new WishContentIdDto(value);
    }
}
