package kr.jenna.plmography.models.vo;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import kr.jenna.plmography.dtos.watched.WatchedContentIdDto;

import java.util.Objects;

@Embeddable
public class WatchedContentId {
    @Column(name = "watched_content_id")
    private String value;

    public WatchedContentId() {
    }

    public WatchedContentId(String value) {
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

        WatchedContentId otherWatchedContentId = (WatchedContentId) other;

        return Objects.equals(value, otherWatchedContentId.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return "WatchedContentId{" +
                "value='" + value + '\'' +
                '}';
    }

    public WatchedContentIdDto toDto() {
        return new WatchedContentIdDto(value);
    }
}
