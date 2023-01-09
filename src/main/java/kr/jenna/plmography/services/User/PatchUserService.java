package kr.jenna.plmography.services.User;

import kr.jenna.plmography.dtos.User.UserDto;
import kr.jenna.plmography.exceptions.NicknameAlreadyExist;
import kr.jenna.plmography.exceptions.UserNotFound;
import kr.jenna.plmography.models.User;
import kr.jenna.plmography.models.VO.Nickname;
import kr.jenna.plmography.models.VO.ProfileImage;
import kr.jenna.plmography.repositories.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PatchUserService {
    private UserRepository userRepository;

    public PatchUserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void update(Long userId, UserDto userDto) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFound(userId));

        Nickname newNickname = new Nickname(userDto.getNickname());
        ProfileImage newProfileImage = new ProfileImage(userDto.getProfileImage());

        if (userRepository.existsByNickname(newNickname)) {
            throw new NicknameAlreadyExist();
        }

        user.update(newNickname, newProfileImage);
    }
}
