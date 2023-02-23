package kr.jenna.plmography.services.user;

import kr.jenna.plmography.dtos.login.LoginResultDto;
import kr.jenna.plmography.dtos.oauth.SocialLoginResultDto;
import kr.jenna.plmography.exceptions.LoginFailed;
import kr.jenna.plmography.models.User;
import kr.jenna.plmography.models.vo.Email;
import kr.jenna.plmography.models.vo.Nickname;
import kr.jenna.plmography.models.vo.Password;
import kr.jenna.plmography.repositories.UserRepository;
import kr.jenna.plmography.utils.JwtUtil;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class LoginService {
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public LoginService(UserRepository userRepository,
                        PasswordEncoder passwordEncoder,
                        JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    public User login(Email email, Password password) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new LoginFailed());

        if (!user.authenticate(password, passwordEncoder)) {

            throw new LoginFailed();
        }

        return user;
    }

    public LoginResultDto socialLogin(SocialLoginResultDto dto) {
        String nickname = dto.getNickname();
        String email = dto.getEmail();

        User foundUser = userRepository.findByNickname(new Nickname(nickname)).orElse(null);

        // 1. 신규 유저일 떄
        if (foundUser == null) {
            // memo. 소셜 로그인의 경우 비밀번호 입력을 받지 않으므로 nickname을 비밀번호로 취급하고자 함
            String passwordForSocialLogin = nickname;

            User user = new User(
                    new Email(email),
                    new Password(passwordForSocialLogin),
                    new Nickname(nickname));

            user.encodePassword(new Password(passwordForSocialLogin), passwordEncoder);

            userRepository.save(user);

            String accessToken = jwtUtil.encode(user.getId());

            return new LoginResultDto(accessToken);
        }

        // 2. 기존 유저일 때
        String accessToken = jwtUtil.encode(foundUser.getId());

        return new LoginResultDto(accessToken);
    }
}
