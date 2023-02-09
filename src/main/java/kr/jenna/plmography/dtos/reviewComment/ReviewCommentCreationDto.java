package kr.jenna.plmography.dtos.reviewComment;

public class ReviewCommentCreationDto {
    private Long id;

    public ReviewCommentCreationDto() {
    }

    public ReviewCommentCreationDto(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
