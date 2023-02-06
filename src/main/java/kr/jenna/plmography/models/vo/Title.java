package kr.jenna.plmography.models.vo;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import kr.jenna.plmography.exceptions.EmptyTitle;

import java.util.Objects;

@Embeddable
public class Title {
    @Column(name = "title")
    private String value;

    public Title() {
    }

    public Title(String value) {
        if (value == null || value.equals("")) {
            throw new EmptyTitle();
        }

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

        Title otherTitle = (Title) other;

        return Objects.equals(value, otherTitle.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return "Title{" +
                "value='" + value + '\'' +
                '}';
    }
}
