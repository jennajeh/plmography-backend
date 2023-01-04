package kr.jenna.plmography.services;

import kr.jenna.plmography.dtos.UserCountDto;
import kr.jenna.plmography.exceptions.UserNotFound;
import kr.jenna.plmography.models.Email;
import kr.jenna.plmography.models.Nickname;
import kr.jenna.plmography.models.User;
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

    public User detail(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFound(userId));

        return user;
    }

    public UserCountDto count(Email email, Nickname nickname) {
        Integer countEmail = userRepository.findAllByEmail(email).size();
        Integer countNickname = userRepository.findAllByNickname(nickname).size();

        return new UserCountDto(countEmail, countNickname);
    }
}
