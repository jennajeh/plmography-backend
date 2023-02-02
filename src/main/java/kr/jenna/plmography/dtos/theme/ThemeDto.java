package kr.jenna.plmography.dtos.theme;

public class ThemeDto {
    private Long id;
    private Long hit;
    private String title;

    public ThemeDto() {
    }

    public ThemeDto(Long id, Long hit, String title) {
        this.id = id;
        this.hit = hit;
        this.title = title;
    }

    public Long getId() {
        return id;
    }

    public Long getHit() {
        return hit;
    }

    public String getTitle() {
        return title;
    }

    public static ThemeDto fake() {
        return new ThemeDto(1L, 4L, "2월 종료 예정작");
    }
}
