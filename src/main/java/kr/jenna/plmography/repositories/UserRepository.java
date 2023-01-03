package kr.jenna.plmography.repositories;

import kr.jenna.plmography.models.Email;
import kr.jenna.plmography.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Boolean existsByEmail(Email email);

    Optional<User> findByEmail(Email email);
}
