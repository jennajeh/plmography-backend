package kr.jenna.plmography.dtos.postComment;

public class PostCommentCreationDto {
    private Long id;

    public PostCommentCreationDto() {
    }

    public PostCommentCreationDto(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
