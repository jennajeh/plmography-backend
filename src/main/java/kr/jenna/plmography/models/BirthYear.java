package kr.jenna.plmography.models;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Embeddable
@NoArgsConstructor
@Getter
public class BirthYear {
    @Column(name = "birth_year")
    private String value;

    public BirthYear(String value) {
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

        BirthYear otherBirthYear = (BirthYear) other;

        return Objects.equals(value, otherBirthYear.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return "BirthYear{" +
                "value='" + value + '\'' +
                '}';
    }
}
