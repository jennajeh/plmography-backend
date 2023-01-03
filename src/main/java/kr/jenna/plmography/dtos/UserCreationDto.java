package kr.jenna.plmography.dtos;

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
}
