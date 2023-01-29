package kr.jenna.plmography.repositories;

import kr.jenna.plmography.models.User;
import kr.jenna.plmography.models.vo.Email;
import kr.jenna.plmography.models.vo.Nickname;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findAllByEmail(Email email);

    List<User> findAllByNickname(Nickname nickname);

    Boolean existsByEmail(Email email);

    Boolean existsByNickname(Nickname nickname);

    Optional<User> findByEmail(Email email);

    Optional<User> findByNickname(Nickname nickname);
}
