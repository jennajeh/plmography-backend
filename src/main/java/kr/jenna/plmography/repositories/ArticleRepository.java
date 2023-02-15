package kr.jenna.plmography.repositories;

import kr.jenna.plmography.models.Article;
import kr.jenna.plmography.models.vo.ContentId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ArticleRepository extends JpaRepository<Article, Long> {
    Page<Article> findAll(Pageable pageable);

    Optional<Article> findByContentId(ContentId contentId);

    List<Article> findTop4ByOrderByCreatedAtDesc();
}
