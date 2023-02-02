package kr.jenna.plmography.dtos.subscribe;

public class MySubscribeDto {
    private Long userId;
    private int followingCount;
    private int followerCount;

    public MySubscribeDto(
            Long userId,
            int followerCount,
            int followingCount) {
        this.userId = userId;
        this.followingCount = followingCount;
        this.followerCount = followerCount;
    }

    public static MySubscribeDto fake() {
        return new MySubscribeDto(1L, 1, 1);
    }

    public Long getUserId() {
        return userId;
    }

    public int getFollowingCount() {
        return followingCount;
    }

    public int getFollowerCount() {
        return followerCount;
    }
}
