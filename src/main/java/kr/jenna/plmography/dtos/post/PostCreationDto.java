package kr.jenna.plmography.dtos.post;

public class PostCreationDto {
    private Long id;
    private boolean isDeleted;

    public PostCreationDto() {
    }

    public PostCreationDto(Long id, boolean isDeleted) {
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
