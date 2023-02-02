package kr.jenna.plmography.dtos.subscribe;

public class OtherSubscribeDto {
    private boolean subscribeStatus;
    private SubscribeUserInfoDto userInfo;
    private int followingCount;
    private int followerCount;

    public OtherSubscribeDto() {
    }

    public OtherSubscribeDto(
            boolean subscribeStatus,
            SubscribeUserInfoDto userInfo, int followingCount,
            int followerCount) {
        this.subscribeStatus = subscribeStatus;
        this.userInfo = userInfo;
        this.followingCount = followingCount;
        this.followerCount = followerCount;
    }

    public boolean isSubscribeStatus() {
        return subscribeStatus;
    }

    public SubscribeUserInfoDto getUserInfo() {
        return userInfo;
    }

    public int getFollowingCount() {
        return followingCount;
    }

    public int getFollowerCount() {
        return followerCount;
    }
}
