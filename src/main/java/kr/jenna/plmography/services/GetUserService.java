package kr.jenna.plmography.services;

import kr.jenna.plmography.exceptions.UserNotFound;
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
}
