package kr.jenna.plmography.dtos.recomment;

public class RecommentCreationDto {
    private Long id;

    public RecommentCreationDto() {
    }

    public RecommentCreationDto(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
