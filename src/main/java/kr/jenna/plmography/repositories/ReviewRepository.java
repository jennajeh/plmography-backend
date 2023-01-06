package kr.jenna.plmography.repositories;

import kr.jenna.plmography.models.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    Page<Review> findAllByUserId(Long userId, Pageable pageable);
}
