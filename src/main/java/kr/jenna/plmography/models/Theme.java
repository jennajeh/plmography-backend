package kr.jenna.plmography.models;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import kr.jenna.plmography.dtos.theme.ThemeDto;
import kr.jenna.plmography.models.vo.Hit;
import kr.jenna.plmography.models.vo.Image;
import kr.jenna.plmography.models.vo.Title;

@Entity
public class Theme {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private Hit hit;

    @Embedded
    private Image image;

    @Embedded
    private Title title;

    public Theme() {
    }

    public Theme(Long id, Hit hit, Image image, Title title) {
        this.id = id;
        this.hit = hit;
        this.image = image;
        this.title = title;
    }

    public Long getId() {
        return id;
    }

    public Hit getHit() {
        return hit;
    }

    public Image getImage() {
        return image;
    }

    public Title getTitle() {
        return title;
    }

    public void updateHit(Long hit) {
        this.hit = new Hit(hit + 1L);
    }

    public static Theme fake() {
        return new Theme(1L, new Hit(1L), new Image("image"), new Title("혼자 보기 좋은 영화 모음"));
    }

    public ThemeDto toThemeDto() {
        return new ThemeDto(id, hit.getValue(), image.getValue(), title.getValue());
    }
}
