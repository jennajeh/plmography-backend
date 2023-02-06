package kr.jenna.plmography.models;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import kr.jenna.plmography.dtos.user.UserCreationDto;
import kr.jenna.plmography.dtos.user.UserDto;
import kr.jenna.plmography.models.vo.BirthYear;
import kr.jenna.plmography.models.vo.Email;
import kr.jenna.plmography.models.vo.FavoriteContentId;
import kr.jenna.plmography.models.vo.Gender;
import kr.jenna.plmography.models.vo.Nickname;
import kr.jenna.plmography.models.vo.Password;
import kr.jenna.plmography.models.vo.ProfileImage;
import kr.jenna.plmography.models.vo.WatchedContentId;
import kr.jenna.plmography.models.vo.WishContentId;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private Email email;

    @Embedded
    private Password password;

    @Embedded
    private Nickname nickname;

    @Embedded
    private Gender gender;

    @Embedded
    private BirthYear birthYear;

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

    public User(Long id, Email email, Password password,
                Nickname nickname, Gender gender, BirthYear birthYear) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.nickname = nickname;
        this.gender = gender;
        this.birthYear = birthYear;
        this.profileImage = new ProfileImage("https://source.boringavatars.com/beam/120/nickname=" + nickname.getValue());
    }

    public User(Email email, Password password,
                Nickname nickname, Gender gender, BirthYear birthYear) {
        this.email = email;
        this.password = password;
        this.nickname = nickname;
        this.gender = gender;
        this.birthYear = birthYear;
        this.profileImage = new ProfileImage("https://source.boringavatars.com/beam/120/?nickname=" + nickname.getValue());
    }

    public static User fake() {
        return new User(
                new Email("jenna@gmail.com"),
                new Password("Test123!"),
                new Nickname("전제나"),
                new Gender("여성"),
                new BirthYear(1994));
    }

    public static User fake(long id) {
        return new User(
                id,
                new Email("jenna@gmail.com"),
                new Password("Test123!"),
                new Nickname("전제나" + id),
                new Gender("여성"),
                new BirthYear(1994));
    }

    public static List<User> fakes(long count) {
        List<User> users = new ArrayList<>();

        for (long i = 1; i <= count; i += 1) {
            User user = User.fake(i);

            users.add(user);
        }

        return users;
    }

    public void encodePassword(Password password, PasswordEncoder passwordEncoder) {
        this.password = new Password(password, passwordEncoder);
    }

    public boolean authenticate(Password password, PasswordEncoder passwordEncoder) {
        return passwordEncoder.matches(password.getValue(), this.password.getValue());
    }

    public void changeProfile(Nickname nickname, ProfileImage profileImage) {
        this.nickname = nickname;
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
                gender.getValue(),
                birthYear.getValue(),
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
