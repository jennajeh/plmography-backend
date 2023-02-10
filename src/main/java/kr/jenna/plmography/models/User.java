package kr.jenna.plmography.models;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import kr.jenna.plmography.dtos.user.UserCreationDto;
import kr.jenna.plmography.dtos.user.UserDto;
import kr.jenna.plmography.models.vo.Email;
import kr.jenna.plmography.models.vo.FavoriteContentId;
import kr.jenna.plmography.models.vo.Nickname;
import kr.jenna.plmography.models.vo.Password;
import kr.jenna.plmography.models.vo.ProfileImage;
import kr.jenna.plmography.models.vo.WatchedContentId;
import kr.jenna.plmography.models.vo.WishContentId;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue
    private Long id;

    @Embedded
    private Email email;

    @Embedded
    private Password password;

    @Embedded
    private Nickname nickname;

    @Embedded
    private ProfileImage profileImage;

    @ElementCollection
    private Set<WishContentId> wishContentIds = new HashSet<>();

    @ElementCollection
    private Set<WatchedContentId> watchedContentIds = new HashSet<>();

    @ElementCollection
    private Set<FavoriteContentId> favoriteContentIds = new HashSet<>();

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    public User() {
    }

    public User(Long id, Email email, Password password, Nickname nickname) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.nickname = nickname;
        this.profileImage = new ProfileImage("https://plmographybucket.s3.ap-northeast-2.amazonaws.com/base_profile.svg");
    }

    public User(Email email, Password password, Nickname nickname) {
        this.email = email;
        this.password = password;
        this.nickname = nickname;
        this.profileImage = new ProfileImage("https://plmographybucket.s3.ap-northeast-2.amazonaws.com/base_profile.svg");
    }

    public static User fake() {
        return new User(
                new Email("jenna@gmail.com"),
                new Password("Test123!"),
                new Nickname("전제나"));
    }

    public static User fake(long id) {
        return new User(
                id,
                new Email("jenna@gmail.com"),
                new Password("Test123!"),
                new Nickname("전제나" + id));
    }

    public static List<User> fakes(long count) {
        List<User> users = new ArrayList<>();

        for (long i = 1; i <= count; i += 1) {
            User user = User.fake(i);

            users.add(user);
        }

        return users;
    }

    public Long getId() {
        return id;
    }

    public Email getEmail() {
        return email;
    }

    public Password getPassword() {
        return password;
    }

    public Nickname getNickname() {
        return nickname;
    }

    public ProfileImage getProfileImage() {
        return profileImage;
    }

    public Set<WishContentId> getWishContentIds() {
        return wishContentIds;
    }

    public Set<WatchedContentId> getWatchedContentIds() {
        return watchedContentIds;
    }

    public Set<FavoriteContentId> getFavoriteContentIds() {
        return favoriteContentIds;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void encodePassword(Password password, PasswordEncoder passwordEncoder) {
        this.password = new Password(password, passwordEncoder);
    }

    public boolean authenticate(Password password, PasswordEncoder passwordEncoder) {
        return passwordEncoder.matches(password.getValue(), this.password.getValue());
    }

    public void changeProfile(Nickname nickname, ProfileImage profileImage) {
        this.nickname = nickname;

        if (profileImage.getValue().equals("")
                || profileImage.getValue() == null) {
            this.profileImage =
                    new ProfileImage("https://plmographybucket.s3.ap-northeast-2.amazonaws.com/base_profile.svg");

            return;
        }

        this.profileImage = profileImage;
    }

    public UserCreationDto toCreateDto() {
        return new UserCreationDto(id, email.getValue(),
                nickname.getValue(), profileImage.getValue());
    }

    public UserDto toMyDto() {
        return new UserDto(
                id,
                email.getValue(),
                nickname.getValue(),
                profileImage.getValue(),
                wishContentIds,
                watchedContentIds,
                favoriteContentIds);
    }

    public UserDto toUserDto() {
        return new UserDto(
                id,
                nickname.getValue(),
                profileImage.getValue(),
                wishContentIds,
                watchedContentIds,
                favoriteContentIds);
    }

    public UserDto toChangeUserProfileDto() {
        return new UserDto(
                id,
                nickname.getValue(),
                profileImage.getValue());
    }

    public void toggleWish(WishContentId wishContentId) {
        if (wishContentIds.contains(wishContentId)) {
            wishContentIds.remove(wishContentId);

            return;
        }

        wishContentIds.add(wishContentId);
    }

    public void toggleWatched(WatchedContentId watchedContentId) {
        if (watchedContentIds.contains(watchedContentId)) {
            watchedContentIds.remove(watchedContentId);

            return;
        }

        watchedContentIds.add(watchedContentId);
    }

    public void toggleFavorite(FavoriteContentId favoriteContentId) {
        if (favoriteContentIds.contains(favoriteContentId)) {
            favoriteContentIds.remove(favoriteContentId);

            return;
        }

        favoriteContentIds.add(favoriteContentId);
    }
}
