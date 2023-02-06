package kr.jenna.plmography.dtos.post;

public class UpdateHitPostResponseDto {
    private Long id;

    public UpdateHitPostResponseDto() {
    }

    public UpdateHitPostResponseDto(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
