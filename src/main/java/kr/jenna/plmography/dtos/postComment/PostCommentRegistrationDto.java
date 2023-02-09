package kr.jenna.plmography.dtos.postComment;

public class PostCommentRegistrationDto {
    private Long postId;
    private String postCommentBody;

    public PostCommentRegistrationDto() {
    }

    public PostCommentRegistrationDto(Long postId, String postCommentBody) {
        this.postId = postId;
        this.postCommentBody = postCommentBody;
    }

    public Long getPostId() {
        return postId;
    }

    public String getPostCommentBody() {
        return postCommentBody;
    }
}
