package kr.jenna.plmography.dtos.Comment;

public class CommentRegistrationDto {
    private Long userId;
    private Long postId;
    private String commentBody;

    public CommentRegistrationDto() {
    }

    public CommentRegistrationDto(Long userId, Long postId, String commentBody) {
        this.userId = userId;
        this.postId = postId;
        this.commentBody = commentBody;
    }

    public Long getUserId() {
        return userId;
    }

    public Long getPostId() {
        return postId;
    }

    public String getCommentBody() {
        return commentBody;
    }
}
