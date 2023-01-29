package kr.jenna.plmography.dtos.comment;

public class CommentModificationRequestDto {
    private Long id;
    private String commentBody;

    public CommentModificationRequestDto() {
    }

    public CommentModificationRequestDto(Long id, String commentBody) {
        this.id = id;
        this.commentBody = commentBody;
    }

    public Long getId() {
        return id;
    }

    public String getCommentBody() {
        return commentBody;
    }
}
