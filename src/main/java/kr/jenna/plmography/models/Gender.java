package kr.jenna.plmography.models;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Embeddable
@NoArgsConstructor
@Getter
public class Gender {
    @Column(name = "gender")
    private String value;

    public Gender(String value) {
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

        Gender otherGender = (Gender) other;

        return Objects.equals(value, otherGender.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return "Gender{" +
                "value='" + value + '\'' +
                '}';
    }
}
