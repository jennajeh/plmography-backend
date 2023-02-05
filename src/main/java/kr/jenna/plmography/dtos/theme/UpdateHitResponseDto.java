package kr.jenna.plmography.dtos.theme;

public class UpdateHitResponseDto {
    private Long id;

    public UpdateHitResponseDto() {
    }

    public UpdateHitResponseDto(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
