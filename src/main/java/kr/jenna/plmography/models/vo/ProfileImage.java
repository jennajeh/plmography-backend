package kr.jenna.plmography.models.VO;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Embeddable
@NoArgsConstructor
@Getter
public class ProfileImage {
    @Column(name = "profile_image", length = 2048)
    private String value;

    public ProfileImage(String value) {
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

        ProfileImage otherProfileImage = (ProfileImage) other;

        return Objects.equals(value, otherProfileImage.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return "ProfileImage{" +
                "value='" + value + '\'' +
                '}';
    }
}
