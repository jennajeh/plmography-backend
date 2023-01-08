package kr.jenna.plmography.repositories;

import kr.jenna.plmography.models.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    Page<Comment> findAllByUserId(Long userId, Pageable pageable);
}
