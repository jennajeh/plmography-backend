package kr.jenna.plmography.models;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
public class Review {
    @Id
    @GeneratedValue
    private Long id;

    @Embedded
    private UserId userId;

    @Embedded
    private ContentId contentId;

    private Long starRate;

    @Embedded
    private ReviewBody reviewBody;

    private Boolean isDeleted;

    @CreationTimestamp
    private LocalDateTime createdAt;

    public Review(Long id, UserId userId, ContentId contentId,
                  Long starRate, ReviewBody reviewBody, Boolean isDeleted) {
        this.id = id;
        this.userId = userId;
        this.contentId = contentId;
        this.starRate = starRate;
        this.reviewBody = reviewBody;
        this.isDeleted = false;
    }

    public static Review fake() {
        return new Review(1L, new UserId(1L), new ContentId(1L),
                4L, new ReviewBody("영화가 재미있어요"), false);
    }

    public void modify(String body) {
        this.reviewBody = new ReviewBody(body);
    }

    public void delete() {
        this.isDeleted = true;
    }
}
