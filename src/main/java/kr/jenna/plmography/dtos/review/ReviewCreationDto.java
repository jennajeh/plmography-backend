package kr.jenna.plmography.dtos.Review;

public class ReviewCreationDto {
    private Long id;
    private boolean isDeleted;

    public ReviewCreationDto(Long id, boolean isDeleted) {
        this.id = id;
        this.isDeleted = isDeleted;
    }

    public Long getId() {
        return id;
    }

    public boolean isDeleted() {
        return isDeleted;
    }
}
