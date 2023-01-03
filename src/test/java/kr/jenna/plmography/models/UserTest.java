package kr.jenna.plmography.models;

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

        user.changePassword(new Password("Test123!"), passwordEncoder);

        assertThat(user.authenticate(new Password("Test123!"), passwordEncoder)).isTrue();
        assertThrows(InvalidPassword.class, () -> {
            user.authenticate(new Password("xxx"), passwordEncoder);
        });
    }

    @Test
    void changeNickname() {
        User user = User.fake();

        String nickname = "강보니";

        user.changeNickname(nickname);

        assertThat(user.getNickname()).isEqualTo(new Nickname("강보니"));
    }

    @Test
    void changeProfileImage() {
        User user = User.fake();

        String profile = "change image";

        user.changeProfileImage(profile);

        assertThat(user.getProfileImage()).isEqualTo(new ProfileImage("change image"));
    }
}
