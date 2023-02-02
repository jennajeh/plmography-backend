package kr.jenna.plmography.dtos.subscribe;

public class FollowingDto {
    private Long userId;
    private String nickname;
    private String profileImage;
    private boolean subscribeStatus;

    public FollowingDto() {
    }

    public FollowingDto(Long userId, String nickname, String profileImage, boolean subscribeStatus) {
        this.userId = userId;
        this.nickname = nickname;
        this.profileImage = profileImage;
        this.subscribeStatus = subscribeStatus;
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

    public boolean isSubscribeStatus() {
        return subscribeStatus;
    }
}
