package kr.jenna.plmography.dtos.postComment;

public class PostCommentModificationRequestDto {
    private String postCommentBody;

    public PostCommentModificationRequestDto() {
    }

    public PostCommentModificationRequestDto(Long id, String postCommentBody) {
        this.postCommentBody = postCommentBody;
    }

    public String getPostCommentBody() {
        return postCommentBody;
    }
}
