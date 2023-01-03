package kr.jenna.plmography.dtos;

public class UserDto {
    private Long id;
    private String email;
    private String nickname;
    private String gender;
    private Integer birthYear;
    private String profileImage;

    public UserDto() {
    }

    public UserDto(Long id, String email,
                   String nickname, String gender,
                   Integer birthYear, String profileImage) {
        this.id = id;
        this.email = email;
        this.nickname = nickname;
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

    public String getGender() {
        return gender;
    }

    public Integer getBirthYear() {
        return birthYear;
    }

    public String getProfileImage() {
        return profileImage;
    }
}
