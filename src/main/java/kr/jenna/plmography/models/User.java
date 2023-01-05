package kr.jenna.plmography.models;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import kr.jenna.plmography.dtos.UserCreationDto;
import kr.jenna.plmography.dtos.UserDto;
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

    @CreationTimestamp
    private LocalDateTime updatedAt;

    public User(Email email, Password password,
                Nickname nickname, Gender gender, BirthYear birthYear) {
        this.email = email;
        this.password = password;
        this.nickname = nickname;
        this.gender = gender;
        this.birthYear = birthYear;
        this.profileImage = new ProfileImage("https://source.boringavatars.com/beam/120/?nickname=" + nickname);
    }

    public static User fake() {
        return new User(new Email("jenna@gmail.com"), new Password("Test123!"),
                new Nickname("전제나"), new Gender("여성"), new BirthYear(1994));
    }

    public void encodePassword(Password password, PasswordEncoder passwordEncoder) {
        this.password = new Password(password, passwordEncoder);
    }

    public boolean authenticate(Password password, PasswordEncoder passwordEncoder) {
        return passwordEncoder.matches(password.getValue(), this.password.getValue());
    }

    public void update(Nickname nickname, ProfileImage profileImage) {
        this.nickname = nickname;

        if (profileImage.getValue().equals("")) {
            return;
        }

        this.profileImage = profileImage;
    }

    public UserCreationDto toCreateDto() {
        return new UserCreationDto(id, email.getValue(),
                nickname.getValue(), profileImage.getValue());
    }

    public UserDto toUserDto() {
        return new UserDto(this.getId(),
                this.email.getValue(),
                this.nickname.getValue(),
                this.password.getValue(),
                this.gender.getValue(),
                this.birthYear.getValue(),
                this.profileImage.getValue());
    }
}
