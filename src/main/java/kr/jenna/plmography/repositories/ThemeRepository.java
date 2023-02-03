package kr.jenna.plmography.repositories;

import kr.jenna.plmography.models.Theme;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ThemeRepository extends JpaRepository<Theme, Long> {
    Page<Theme> findAll(Pageable pageable);

    List<Theme> findTop3ByOrderByHitDesc();
}
