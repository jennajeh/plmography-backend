package kr.jenna.plmography.dtos.user;

public class UserProfileRequestDto {
    private String nickname;
    private String profileImage;

    public UserProfileRequestDto() {
    }

    public UserProfileRequestDto(String nickname, String profileImage) {
        this.nickname = nickname;
        this.profileImage = profileImage;
    }

    public String getNickname() {
        return nickname;
    }

    public String getProfileImage() {
        return profileImage;
    }
}
