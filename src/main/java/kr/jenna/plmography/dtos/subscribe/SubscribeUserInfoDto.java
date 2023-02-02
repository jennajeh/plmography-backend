package kr.jenna.plmography.dtos.subscribe;

public class SubscribeUserInfoDto {
    private Long userId;
    private String nickname;
    private String profileImage;

    public SubscribeUserInfoDto() {
    }

    public SubscribeUserInfoDto(Long userId, String nickname, String profileImage) {
        this.userId = userId;
        this.nickname = nickname;
        this.profileImage = profileImage;
    }

    public Long getUserId() {
        return userId;
    }

    public String getNickname() {
        return nickname;
    }

    public String getProfileImage() {
        return profileImage;
    }
}
