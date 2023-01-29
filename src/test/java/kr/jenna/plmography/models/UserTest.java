package kr.jenna.plmography.models;

import kr.jenna.plmography.dtos.user.UserCreationDto;
import kr.jenna.plmography.dtos.user.UserDto;
import kr.jenna.plmography.exceptions.InvalidPassword;
import kr.jenna.plmography.models.vo.BirthYear;
import kr.jenna.plmography.models.vo.Email;
import kr.jenna.plmography.models.vo.Gender;
import kr.jenna.plmography.models.vo.Nickname;
import kr.jenna.plmography.models.vo.Password;
import kr.jenna.plmography.models.vo.ProfileImage;
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

        user.changeProfile(new Nickname(nickname), new ProfileImage(profileImage));

        assertThat(user.getNickname()).isEqualTo(new Nickname("강보니"));
        assertThat(user.getProfileImage()).isEqualTo(new ProfileImage("new profile"));
    }

    @Test
    void toUserDto() {
        User user = new User(1L, new Email("jenna@gmail.com"), new Password("Test123!"),
                new Nickname("jenna"), new Gender("여성"), new BirthYear(1994));

        UserDto userDto = user.toUserDto();

        assertThat(userDto).isEqualTo(new UserDto(1L, "jenna@gmail.com",
                "jenna", "여성", 1994, "https://source.boringavatars.com/beam/120/nickname=jenna"));
    }

    @Test
    void toCreateDto() {
        User user = new User(1L, new Email("jenna@gmail.com"), new Password("Test123!"),
                new Nickname("jenna"), new Gender("여성"), new BirthYear(1994));

        UserCreationDto userCreateDto = user.toCreateDto();

        assertThat(userCreateDto).isEqualTo(
                new UserCreationDto(1L, "jenna@gmail.com", "jenna", "https://source.boringavatars.com/beam/120/nickname=jenna"));
    }
}
