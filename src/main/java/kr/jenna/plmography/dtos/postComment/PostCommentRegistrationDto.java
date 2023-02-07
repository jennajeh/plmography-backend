package kr.jenna.plmography.dtos.postComment;

public class PostCommentRegistrationDto {
    private Long userId;
    private Long postId;
    private String postCommentBody;

    public PostCommentRegistrationDto() {
    }

    public PostCommentRegistrationDto(Long userId, Long postId, String postCommentBody) {
        this.userId = userId;
        this.postId = postId;
        this.postCommentBody = postCommentBody;
    }

    public Long getUserId() {
        return userId;
    }

    public Long getPostId() {
        return postId;
    }

    public String getPostCommentBody() {
        return postCommentBody;
    }
}
