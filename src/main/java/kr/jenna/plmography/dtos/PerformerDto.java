package kr.jenna.plmography.dtos;

import java.util.Objects;

public class PerformerDto {
    private Long id;
    private String tmdbContentId;
    private String originalName;
    private String characterName;
    private String department;
    private String profileImageUrl;

    public PerformerDto() {
    }

    public PerformerDto(Long id, String tmdbContentId,
                        String originalName, String characterName,
                        String department, String profileImage) {
        this.id = id;
        this.tmdbContentId = tmdbContentId;
        this.originalName = originalName;
        this.characterName = characterName;
        this.department = department;
        this.profileImageUrl = profileImageUrl;
    }

    public Long getId() {
        return id;
    }

    public String getTmdbContentId() {
        return tmdbContentId;
    }

    public String getOriginalName() {
        return originalName;
    }

    public String getCharacterName() {
        return characterName;
    }

    public String getDepartment() {
        return department;
    }

    public String getProfileImageUrl() {
        return profileImageUrl;
    }

    @Override
    public boolean equals(Object other) {
        PerformerDto otherPerformer = (PerformerDto) other;
        return Objects.equals(id, otherPerformer.id)
                && Objects.equals(tmdbContentId, otherPerformer.tmdbContentId)
                && Objects.equals(originalName, otherPerformer.originalName)
                && Objects.equals(characterName, otherPerformer.characterName)
                && Objects.equals(department, otherPerformer.department)
                && Objects.equals(profileImageUrl, otherPerformer.profileImageUrl);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, tmdbContentId, originalName,
                characterName, department, profileImageUrl);
    }
}
