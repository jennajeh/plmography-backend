package kr.jenna.plmography.repositories;

import kr.jenna.plmography.models.Content;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ContentRepository extends JpaRepository<Content, Long> {
    Page<Content> findAll(Pageable pageable);

    Optional<Content> findByTmdbId(String tmdbId);
}
