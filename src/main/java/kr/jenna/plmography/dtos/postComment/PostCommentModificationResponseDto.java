package kr.jenna.plmography.dtos.postComment;

public class PostCommentModificationResponseDto {
    private Long id;

    public PostCommentModificationResponseDto() {
    }

    public PostCommentModificationResponseDto(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
