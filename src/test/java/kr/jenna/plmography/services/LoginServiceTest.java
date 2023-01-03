package kr.jenna.plmography.services;

import kr.jenna.plmography.exceptions.LoginFailed;
import kr.jenna.plmography.models.Email;
import kr.jenna.plmography.models.Password;
import kr.jenna.plmography.models.User;
import kr.jenna.plmography.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class LoginServiceTest {
    private LoginService loginService;
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    @BeforeEach
    void setup() {
        userRepository = mock(UserRepository.class);
        passwordEncoder = new Argon2PasswordEncoder(16, 32, 1, 1 << 14, 2);
        loginService = new LoginService(userRepository, passwordEncoder);
    }

    @Test
    void loginSuccess() {
        Email email = new Email("jenna@gmail.com");
        Password password = new Password("Test123!");

        User user = User.fake();
        user.encodePassword(password, passwordEncoder);

        given(userRepository.findByEmail(email)).willReturn(Optional.of(user));

        assertThat(loginService.login(email, password).getEmail())
                .isEqualTo(email);
    }

    @Test
    void loginWithNotExistEmail() {
        Email email = new Email("jenna@gmail.com");
        Password password = new Password("Test123!");

        assertThrows(LoginFailed.class, () -> loginService.login(email, password));
    }

    @Test
    void loginWithWrongPassword() {
        Email email = new Email("jenna@gmail.com");
        Password password = new Password("Test123!");

        User user = User.fake();
        user.encodePassword(new Password("tEsT123!"), passwordEncoder);

        given(userRepository.findByEmail(email)).willReturn(Optional.of(user));

        assertThrows(LoginFailed.class, () -> loginService.login(email, password));
    }
}
