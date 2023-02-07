package kr.jenna.plmography.dtos.postComment;

public class PostCommentModificationRequestDto {
    private Long id;
    private String postCommentBody;

    public PostCommentModificationRequestDto() {
    }

    public PostCommentModificationRequestDto(Long id, String postCommentBody) {
        this.id = id;
        this.postCommentBody = postCommentBody;
    }

    public Long getId() {
        return id;
    }

    public String getPostCommentBody() {
        return postCommentBody;
    }
}
