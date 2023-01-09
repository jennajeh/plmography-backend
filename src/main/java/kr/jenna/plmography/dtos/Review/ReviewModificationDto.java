package kr.jenna.plmography.dtos.Review;

public class ReviewModificationDto {
    private Long id;

    public ReviewModificationDto() {
    }

    public ReviewModificationDto(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
