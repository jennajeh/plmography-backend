package kr.jenna.plmography.repositories;

import kr.jenna.plmography.models.Content;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface ContentRepository extends JpaRepository<Content, Long>, JpaSpecificationExecutor<Content> {
    Page<Content> findAll(Pageable pageable);

    Page<Content> findAll(Specification<Content> spec, Pageable pageable);

    Optional<Content> findByTmdbId(String tmdbId);
}
