package kr.jenna.plmography.services;

import kr.jenna.plmography.dtos.UserDto;
import kr.jenna.plmography.exceptions.NicknameAlreadyExist;
import kr.jenna.plmography.exceptions.UserNotFound;
import kr.jenna.plmography.models.Nickname;
import kr.jenna.plmography.models.Password;
import kr.jenna.plmography.models.ProfileImage;
import kr.jenna.plmography.models.User;
import kr.jenna.plmography.repositories.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UpdateUserService {
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    public UpdateUserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void update(Long userId, UserDto userDto) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFound(userId));

        Password newPassword = new Password(userDto.getPassword());
        Nickname newNickname = new Nickname(userDto.getNickname());
        ProfileImage newProfileImage = new ProfileImage(userDto.getProfileImage());

        if (userRepository.existsByNickname(newNickname)) {
            throw new NicknameAlreadyExist();
        }

        user.encodePassword(newPassword, passwordEncoder);

        user.update(newPassword, newNickname, newProfileImage);
    }
}
