package kr.jenna.plmography.dtos.user;

import kr.jenna.plmography.models.vo.FavoriteContentId;
import kr.jenna.plmography.models.vo.WatchedContentId;
import kr.jenna.plmography.models.vo.WishContentId;

import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public class UserDto {
    private Long id;
    private String email;
    private String nickname;
    private String profileImage;
    private Set<Long> wishContentIds;
    private Set<Long> watchedContentIds;
    private Set<Long> favoriteContentIds;

    public UserDto() {
    }

    public UserDto(Long id,
                   String email,
                   String nickname,
                   String profileImage,
                   Set<WishContentId> wishContentIds,
                   Set<WatchedContentId> watchedContentIds,
                   Set<FavoriteContentId> favoriteContentIds) {
        this.id = id;
        this.email = email;
        this.nickname = nickname;
        this.profileImage = profileImage;
        setWishContentIds(wishContentIds);
        setWatchedContentIds(watchedContentIds);
        setFavoriteContentIds(favoriteContentIds);
    }

    public UserDto(Long id,
                   String nickname,
                   String profileImage,
                   Set<WishContentId> wishContentIds,
                   Set<WatchedContentId> watchedContentIds,
                   Set<FavoriteContentId> favoriteContentIds) {
        this.id = id;
        this.nickname = nickname;
        this.profileImage = profileImage;
        setWishContentIds(wishContentIds);
        setWatchedContentIds(watchedContentIds);
        setFavoriteContentIds(favoriteContentIds);
    }

    public UserDto(Long id, String nickname, String profileImage) {
        this.id = id;
        this.nickname = nickname;
        this.profileImage = profileImage;
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getNickname() {
        return nickname;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public Set<Long> getWishContentIds() {
        return wishContentIds;
    }

    public Set<Long> getWatchedContentIds() {
        return watchedContentIds;
    }

    public Set<Long> getFavoriteContentIds() {
        return favoriteContentIds;
    }

    private void setWishContentIds(Set<WishContentId> wishContentIds) {
        this.wishContentIds = wishContentIds.stream()
                .map(WishContentId::getValue)
                .collect(Collectors.toSet());
    }

    private void setWatchedContentIds(Set<WatchedContentId> watchedContentIds) {
        this.watchedContentIds = watchedContentIds.stream()
                .map(WatchedContentId::getValue)
                .collect(Collectors.toSet());
    }

    private void setFavoriteContentIds(Set<FavoriteContentId> favoriteContentIds) {
        this.favoriteContentIds = favoriteContentIds.stream()
                .map(FavoriteContentId::getValue)
                .collect(Collectors.toSet());
    }

    @Override
    public String toString() {
        return "UserDto{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", nickname='" + nickname + '\'' +
                ", profileImage='" + profileImage + '\'' +
                ", wishContentIds='" + wishContentIds + '\'' +
                ", watchedContentIds=" + watchedContentIds +
                ", favoriteContentIds='" + favoriteContentIds + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object other) {
        UserDto userDto = (UserDto) other;
        return Objects.equals(id, userDto.id)
                && Objects.equals(email, userDto.email)
                && Objects.equals(nickname, userDto.nickname)
                && Objects.equals(profileImage, userDto.profileImage)
                && Objects.equals(wishContentIds, userDto.wishContentIds)
                && Objects.equals(watchedContentIds, userDto.watchedContentIds)
                && Objects.equals(favoriteContentIds, userDto.favoriteContentIds);
    }
}
