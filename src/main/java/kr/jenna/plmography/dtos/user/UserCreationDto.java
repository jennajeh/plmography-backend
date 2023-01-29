package kr.jenna.plmography.dtos.User;

import java.util.Objects;

public class UserCreationDto {
    private Long id;
    private String email;
    private String nickname;
    private String profileImage;

    public UserCreationDto() {
    }

    public UserCreationDto(Long id, String email, String nickname, String profileImage) {
        this.id = id;
        this.email = email;
        this.nickname = nickname;
        this.profileImage = profileImage;
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getNickname() {
        return nickname;
    }

    public String getProfileImage() {
        return profileImage;
    }

    @Override
    public String toString() {
        return "UserCreationDto{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", nickname='" + nickname + '\'' +
                ", profileImage='" + profileImage + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object other) {
        UserCreationDto that = (UserCreationDto) other;
        return Objects.equals(id, that.id)
                && Objects.equals(email, that.email)
                && Objects.equals(nickname, that.nickname)
                && Objects.equals(profileImage, that.profileImage);
    }
}
