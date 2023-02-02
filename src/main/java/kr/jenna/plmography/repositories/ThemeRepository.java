package kr.jenna.plmography.repositories;

import kr.jenna.plmography.models.Theme;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ThemeRepository extends JpaRepository<Theme, Long> {
    Page<Theme> findAll(Pageable pageable);
}
