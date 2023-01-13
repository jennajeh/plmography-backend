package kr.jenna.plmography.repositories;

import kr.jenna.plmography.models.Review;
import kr.jenna.plmography.models.VO.UserId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ReviewRepository extends JpaRepository<Review, Long> {
//    Page<Review> findAll(Pageable pageable);

    @Query("select r from Review r where r.userId != :userId")
    Page<Review> findAllByUserIdNotLike(@Param("userId") UserId userId, Pageable pageable);

    Optional<Review> findByUserId(UserId userId);
}
