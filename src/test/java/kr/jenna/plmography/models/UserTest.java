package kr.jenna.plmography.models;

import kr.jenna.plmography.dtos.UserCreationDto;
import kr.jenna.plmography.dtos.UserDto;
import kr.jenna.plmography.exceptions.InvalidPassword;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class UserTest {

    @Test
    void creation() {
        User user = new User(new Email("jenna@gmail.com"), new Password("Test123!"),
                new Nickname("전제나"), new Gender("여성"), new BirthYear(1994));

        assertThat(user.getEmail()).isEqualTo(new Email("jenna@gmail.com"));
        assertThat(user.getNickname()).isEqualTo(new Nickname("전제나"));
        assertThat(user.getBirthYear()).isEqualTo(new BirthYear(1994));
    }

    @Test
    void authenticate() {
        PasswordEncoder passwordEncoder = new Argon2PasswordEncoder(16, 32, 1, 1 << 14, 2);

        User user = new User();

        user.encodePassword(new Password("Test123!"), passwordEncoder);

        assertThat(user.authenticate(new Password("Test123!"), passwordEncoder)).isTrue();
        assertThrows(InvalidPassword.class, () -> {
            user.authenticate(new Password("xxx"), passwordEncoder);
        });
    }

    @Test
    void update() {
        User user = User.fake();

        String nickname = "강보니";
        String profileImage = "new profile";

        user.update(new Nickname(nickname), new ProfileImage(profileImage));

        assertThat(user.getNickname()).isEqualTo(new Nickname("강보니"));
        assertThat(user.getProfileImage()).isEqualTo(new ProfileImage("new profile"));
    }

    @Test
    void toUserDto() {
        User user = new User(1L, new Email("jenna@gmail.com"), new Password("Test123!"),
                new Nickname("jenna"), new Gender("여성"),
                new BirthYear(1994), new ProfileImage("https://source.boringavatars.com/beam/120/?nickname=jenna"));

        UserDto userDto = user.toUserDto();

        assertThat(userDto).isEqualTo(new UserDto(1L, "jenna@gmail.com",
                "jenna", "Test123!", "여성", 1994, "https://source.boringavatars.com/beam/120/?nickname=jenna"));
    }

    @Test
    void toCreateDto() {
        User user = new User(1L, new Email("jenna@gmail.com"), new Password("Test123!"),
                new Nickname("jenna"), new Gender("여성"), new BirthYear(1994), new ProfileImage("https://source.boringavatars.com/beam/120/?nickname=jenna"));

        UserCreationDto userCreateDto = user.toCreateDto();

        assertThat(userCreateDto).isEqualTo(
                new UserCreationDto(1L, "jenna@gmail.com", "jenna", "https://source.boringavatars.com/beam/120/?nickname=jenna"));
    }
}
