package kr.jenna.plmography.models;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
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
    private Gender gender;

    @Embedded
    private BirthYear birthYear;

    @Embedded
    private ProfileImage profileImage;

    @ElementCollection
    private List<FollowerId> followerIds = new ArrayList<>();

    @ElementCollection
    private List<FollowingId> followingIds = new ArrayList<>();

    @ElementCollection
    private List<ContentId> bookmarkContents = new ArrayList<>();

    @ElementCollection
    private List<ContentId> bookmarkThemes = new ArrayList<>();

    @ElementCollection
    private List<ContentId> watchedList = new ArrayList<>();

    @ElementCollection
    private List<ContentId> favoriteContents = new ArrayList<>();

    @CreationTimestamp
    private LocalDateTime createdAt;

    public User(Long id, Email email, Password password,
                Nickname nickname, Gender gender, BirthYear birthYear,
                ProfileImage profileImage) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.nickname = nickname;
        this.gender = gender;
        this.birthYear = birthYear;
        this.profileImage = profileImage;
    }

    public static User fake() {
        return new User(1L, new Email("jenna@gmail.com"), new Password("Test123!"), new Nickname("전제나")
                , new Gender("여성"), new BirthYear(1994), new ProfileImage("profile"));
    }

    public void changePassword(Password password, PasswordEncoder passwordEncoder) {
        this.password = new Password(password, passwordEncoder);
    }

    public boolean authenticate(Password password, PasswordEncoder passwordEncoder) {
        return passwordEncoder.matches(password.getValue(), this.password.getValue());
    }

    public void changeNickname(String nickname) {
        this.nickname = new Nickname(nickname);
    }

    public void changeProfileImage(String profileImage) {
        this.profileImage = new ProfileImage(profileImage);
    }
}
