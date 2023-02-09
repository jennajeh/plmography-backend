package kr.jenna.plmography.dtos.like;

public class LikeDto {
    private Long id;
    private Long postId;
    private Long userId;

    public LikeDto() {
    }

    public LikeDto(Long id, Long postId, Long userId) {
        this.id = id;
        this.postId = postId;
        this.userId = userId;
    }

    public Long getId() {
        return id;
    }

    public Long getPostId() {
        return postId;
    }

    public Long getUserId() {
        return userId;
    }

    public static LikeDto fake() {
        return new LikeDto(1L, 1L, 1L);
    }
}
