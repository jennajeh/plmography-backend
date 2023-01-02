package kr.jenna.plmography.models;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Embeddable
@Getter
@NoArgsConstructor
public class Nickname {
    @Column(name = "nickname")
    private String value;

    public Nickname(String value) {
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

        Nickname otherNickname = (Nickname) other;

        return Objects.equals(value, otherNickname.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return "Nickname{" +
                "value='" + value + '\'' +
                '}';
    }
}
