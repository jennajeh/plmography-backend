package kr.jenna.plmography.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Performer {
    @Id
    @GeneratedValue
    private Long id;

    private String tmdbContentId;

    private String originalName;

    private String characterName;

    private String department;

    private String profileImageUrl;

    @Builder
    public Performer(Long id, String tmdbContentId,
                     String originalName, String characterName,
                     String department, String profileImageUrl) {
        this.id = id;
        this.tmdbContentId = tmdbContentId;
        this.originalName = originalName;
        this.characterName = characterName;
        this.department = department;
        this.profileImageUrl = profileImageUrl;
    }
}
