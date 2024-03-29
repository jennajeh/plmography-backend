package kr.jenna.plmography.services.user;

import kr.jenna.plmography.dtos.user.UserRegistrationDto;
import kr.jenna.plmography.exceptions.EmailAlreadyExist;
import kr.jenna.plmography.exceptions.NicknameAlreadyExist;
import kr.jenna.plmography.exceptions.PasswordNotMatch;
import kr.jenna.plmography.models.User;
import kr.jenna.plmography.models.vo.Email;
import kr.jenna.plmography.models.vo.Nickname;
import kr.jenna.plmography.models.vo.Password;
import kr.jenna.plmography.repositories.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@Transactional
public class CreateUserService {
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    public CreateUserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User create(UserRegistrationDto userRegistrationDto) {
        Email email = new Email(userRegistrationDto.getEmail());
        Password password = new Password(userRegistrationDto.getPassword());
        Nickname nickname = new Nickname(userRegistrationDto.getNickname());

        if (userRepository.existsByEmail(email)) {
            throw new EmailAlreadyExist(email);
        }

        if (userRepository.existsByNickname(nickname)) {
            throw new NicknameAlreadyExist();
        }

        if (!password.getValue().equals(userRegistrationDto.getPasswordCheck())) {
            throw new PasswordNotMatch();
        }

        User user = new User(email, password, nickname, LocalDateTime.now(), LocalDateTime.now());

        user.encodePassword(password, passwordEncoder);

        userRepository.save(user);

        return user;
    }
}
