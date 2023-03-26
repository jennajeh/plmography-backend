package kr.jenna.plmography.repositories;

import kr.jenna.plmography.models.Content;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ContentRepository extends JpaRepository<Content, Long>, JpaSpecificationExecutor<Content> {
    Page<Content> findAll(Pageable pageable);

    Page<Content> findAll(Specification<Content> spec, Pageable pageable);

    List<Content> findAll(Specification<Content> spec);

    List<Content> findAllByPopularityGreaterThanOrderByPopularityDesc(int popularity);

    Optional<Content> findByTmdbId(Long tmdbId);

    @Query("select c from Content c where c.expiredDateOnNetflix = :expiredDateOnNetflix")
    Page<Content> findAllByExpiredDateOnNetflix(@Param("expiredDateOnNetflix") Long expiredDateOnNetflix, Pageable pageable);
}
