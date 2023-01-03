package kr.jenna.plmography.repositories;

import kr.jenna.plmography.models.Email;
import kr.jenna.plmography.models.Nickname;
import kr.jenna.plmography.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Boolean existsByEmail(Email email);

    Boolean existsByNickname(Nickname nickname);

    Optional<User> findByEmail(Email email);
}
