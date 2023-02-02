package kr.jenna.plmography.models.vo;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.util.Objects;

@Embeddable
public class Hit {
    @Column(name = "hit")
    private Long value;

    public Hit() {
    }

    public Hit(Long value) {
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

        Hit otherHit = (Hit) other;

        return Objects.equals(value, otherHit.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return "Hit{" +
                "value='" + value + '\'' +
                '}';
    }
}
