package kr.jenna.plmography.dtos.post;

public class PostModificationResponseDto {
    private Long id;

    public PostModificationResponseDto() {
    }

    public PostModificationResponseDto(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
