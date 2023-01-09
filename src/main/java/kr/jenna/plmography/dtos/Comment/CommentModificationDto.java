package kr.jenna.plmography.dtos.Comment;

public class CommentModificationDto {
    private Long id;

    public CommentModificationDto() {
    }

    public CommentModificationDto(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
