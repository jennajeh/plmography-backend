package kr.jenna.plmography.services.user;

import kr.jenna.plmography.exceptions.LoginFailed;
import kr.jenna.plmography.models.User;
import kr.jenna.plmography.models.vo.Email;
import kr.jenna.plmography.models.vo.Password;
import kr.jenna.plmography.repositories.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class LoginService {
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    public LoginService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User login(Email email, Password password) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new LoginFailed());

        if (!user.authenticate(password, passwordEncoder)) {

            throw new LoginFailed();
        }

        return user;
    }
}
