package kr.jenna.plmography.dtos.Recomment;

public class RecommentRegistrationDto {
    private Long commentId;
    private String recommentBody;
    private Long userId;
    private Long postId;

    public RecommentRegistrationDto() {
    }

    public RecommentRegistrationDto(Long commentId,
                                    String recommentBody,
                                    Long userId,
                                    Long postId) {
        this.commentId = commentId;
        this.recommentBody = recommentBody;
        this.userId = userId;
        this.postId = postId;
    }

    public Long getCommentId() {
        return commentId;
    }

    public String getRecommentBody() {
        return recommentBody;
    }

    public Long getUserId() {
        return userId;
    }

    public Long getPostId() {
        return postId;
    }
}
