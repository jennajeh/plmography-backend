package kr.jenna.plmography.repositories;

import kr.jenna.plmography.models.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {
}
