package kr.jenna.plmography.repositories;

import kr.jenna.plmography.models.TvDrama;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TvDramaRepository extends JpaRepository<TvDrama, Long> {
    Page<TvDrama> findAll(Pageable pageable);
}
