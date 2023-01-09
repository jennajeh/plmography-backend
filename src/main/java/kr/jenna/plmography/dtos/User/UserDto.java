package kr.jenna.plmography.dtos.User;

import java.util.Objects;

public class UserDto {
    private Long id;
    private String email;
    private String nickname;
    private String password;
    private String gender;
    private Integer birthYear;
    private String profileImage;

    public UserDto() {
    }

    public UserDto(Long id, String email,
                   String nickname, String password, String gender,
                   Integer birthYear, String profileImage) {
        this.id = id;
        this.email = email;
        this.nickname = nickname;
        this.password = password;
        this.gender = gender;
        this.birthYear = birthYear;
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

    public String getPassword() {
        return password;
    }

    public String getGender() {
        return gender;
    }

    public Integer getBirthYear() {
        return birthYear;
    }

    public String getProfileImage() {
        return profileImage;
    }

    @Override
    public String toString() {
        return "UserDto{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", nickname='" + nickname + '\'' +
                ", password='" + password + '\'' +
                ", gender='" + gender + '\'' +
                ", birthYear=" + birthYear +
                ", profileImage='" + profileImage + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object other) {
        UserDto userDto = (UserDto) other;
        return Objects.equals(id, userDto.id)
                && Objects.equals(email, userDto.email)
                && Objects.equals(nickname, userDto.nickname)
                && Objects.equals(password, userDto.password)
                && Objects.equals(gender, userDto.gender)
                && Objects.equals(birthYear, userDto.birthYear)
                && Objects.equals(profileImage, userDto.profileImage);
    }
}
