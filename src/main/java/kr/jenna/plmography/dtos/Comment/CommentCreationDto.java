package kr.jenna.plmography.dtos.Comment;

public class CommentCreationDto {
    private Long id;

    public CommentCreationDto() {
    }

    public CommentCreationDto(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
