package kr.jenna.plmography.services;

import kr.jenna.plmography.dtos.UserRegistrationDto;
import kr.jenna.plmography.exceptions.EmailAlreadyExist;
import kr.jenna.plmography.exceptions.PasswordNotMatch;
import kr.jenna.plmography.models.BirthYear;
import kr.jenna.plmography.models.Email;
import kr.jenna.plmography.models.Gender;
import kr.jenna.plmography.models.Nickname;
import kr.jenna.plmography.models.Password;
import kr.jenna.plmography.models.User;
import kr.jenna.plmography.repositories.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        Password passwordCheck = new Password(userRegistrationDto.getPasswordCheck());
        Nickname nickname = new Nickname(userRegistrationDto.getNickname());
        Gender gender = new Gender(userRegistrationDto.getGender());
        BirthYear birthYear = new BirthYear(userRegistrationDto.getBirthYear());

        if (userRepository.existsByEmail(email)) {
            throw new EmailAlreadyExist(email);
        }

        if (!password.getValue().equals(passwordCheck.getValue())) {
            throw new PasswordNotMatch();
        }

        User user = new User(email, password, nickname, gender, birthYear);

        user.changePassword(password, passwordEncoder);

        userRepository.save(user);

        return user;
    }
}
