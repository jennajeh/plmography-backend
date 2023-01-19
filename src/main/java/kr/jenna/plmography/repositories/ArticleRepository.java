package kr.jenna.plmography.repositories;

import kr.jenna.plmography.models.Article;
import kr.jenna.plmography.models.VO.ContentId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ArticleRepository extends JpaRepository<Article, Long> {
    Page<Article> findAll(Pageable pageable);

    Optional<Article> findByContentId(ContentId contentId);
}
