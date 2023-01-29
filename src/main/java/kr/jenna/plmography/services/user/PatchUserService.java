package kr.jenna.plmography.services.user;

import kr.jenna.plmography.dtos.user.UserProfileRequestDto;
import kr.jenna.plmography.exceptions.NicknameAlreadyExist;
import kr.jenna.plmography.exceptions.UserNotFound;
import kr.jenna.plmography.models.User;
import kr.jenna.plmography.models.vo.Nickname;
import kr.jenna.plmography.models.vo.ProfileImage;
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

    public User update(Long userId, UserProfileRequestDto userProfileRequestDto) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFound(userId));

        Nickname nickname = new Nickname(userProfileRequestDto.getNickname());
        ProfileImage profileImage = new ProfileImage(userProfileRequestDto.getProfileImage());

        if (userRepository.existsByNickname(nickname)) {
            throw new NicknameAlreadyExist();
        }

        user.changeProfile(nickname, profileImage);

        return user;
    }
}
