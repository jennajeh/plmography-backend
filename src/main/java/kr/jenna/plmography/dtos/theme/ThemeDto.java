package kr.jenna.plmography.dtos.theme;

public class ThemeDto {
    private Long id;
    private Long hit;
    private String image;
    private String title;

    public ThemeDto() {
    }

    public ThemeDto(Long id, Long hit, String image, String title) {
        this.id = id;
        this.hit = hit;
        this.image = image;
        this.title = title;
    }

    public Long getId() {
        return id;
    }

    public Long getHit() {
        return hit;
    }

    public String getImage() {
        return image;
    }

    public String getTitle() {
        return title;
    }

    public static ThemeDto fake() {
        return new ThemeDto(1L, 4L, "image", "2월 종료 예정작");
    }
}
