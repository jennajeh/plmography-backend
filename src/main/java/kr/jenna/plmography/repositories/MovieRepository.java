package kr.jenna.plmography.repositories;

import kr.jenna.plmography.models.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie, Long> {
    Page<Movie> findAll(Pageable pageable);
}
