package kr.jenna.plmography.dtos.Review;

public class ReviewModificationResponseDto {
    private Long id;

    public ReviewModificationResponseDto() {
    }

    public ReviewModificationResponseDto(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
