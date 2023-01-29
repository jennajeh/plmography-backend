package kr.jenna.plmography.dtos.comment;

public class CommentModificationResponseDto {
    private Long id;

    public CommentModificationResponseDto() {
    }

    public CommentModificationResponseDto(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
