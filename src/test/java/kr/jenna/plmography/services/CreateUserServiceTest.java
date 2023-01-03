package kr.jenna.plmography.services;

import kr.jenna.plmography.dtos.UserRegistrationDto;
import kr.jenna.plmography.exceptions.EmailAlreadyExist;
import kr.jenna.plmography.models.Email;
import kr.jenna.plmography.models.User;
import kr.jenna.plmography.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class CreateUserServiceTest {
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private CreateUserService createUserService;

    @BeforeEach
    void setup() {
        userRepository = mock(UserRepository.class);
        passwordEncoder = new Argon2PasswordEncoder(16, 32, 1, 1 << 14, 2);
        createUserService = new CreateUserService(userRepository, passwordEncoder);
    }

    @Test
    void create() {
        UserRegistrationDto userRegistrationDto = new UserRegistrationDto(
                "jenna@gmail.com", "Test123!", "Test123!", "전제나", "여성", 1994);

        User user = createUserService.create(userRegistrationDto);

        assertThat(user).isNotNull();

        verify(userRepository).save(user);
    }

    @Test
    void createWithExistedEmail() {
        UserRegistrationDto userRegistrationDto = new UserRegistrationDto(
                "jenna@gmail.com", "Test123!", "Test123!", "전제나", "여성", 1994);

        given(userRepository.existsByEmail(new Email("jenna@gmail.com")))
                .willReturn(true);

        assertThrows(EmailAlreadyExist.class,
                () -> createUserService.create(userRegistrationDto));
    }
}
