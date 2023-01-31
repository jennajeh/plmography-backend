package kr.jenna.plmography.models.vo;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import kr.jenna.plmography.dtos.favorite.FavoriteContentIdDto;

import java.util.Objects;

@Embeddable
public class FavoriteContentId {
    @Column(name = "favorite_content_id")
    private Long value;

    public FavoriteContentId() {
    }

    public FavoriteContentId(Long value) {
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

        FavoriteContentId otherFavoriteContentId = (FavoriteContentId) other;

        return Objects.equals(value, otherFavoriteContentId.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return "FavoriteContentId{" +
                "value='" + value + '\'' +
                '}';
    }

    public FavoriteContentIdDto toDto() {
        return new FavoriteContentIdDto(value);
    }
}
