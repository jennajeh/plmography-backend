package kr.jenna.plmography.services.user;

import kr.jenna.plmography.dtos.user.UserCountDto;
import kr.jenna.plmography.exceptions.UserNotFound;
import kr.jenna.plmography.models.User;
import kr.jenna.plmography.models.vo.Email;
import kr.jenna.plmography.models.vo.Nickname;
import kr.jenna.plmography.repositories.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class GetUserService {
    private UserRepository userRepository;

    public GetUserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User profile(Nickname nickname) {
        User user = userRepository.findByNickname(nickname)
                .orElseThrow(() -> new UserNotFound());

        return user;
    }

    public UserCountDto count(Email email, Nickname nickname) {
        Integer countEmail = userRepository.findAllByEmail(email).size();
        Integer countNickname = userRepository.findAllByNickname(nickname).size();

        return new UserCountDto(countEmail, countNickname);
    }

    public User findMe(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFound(userId));

        return user;
    }
}
