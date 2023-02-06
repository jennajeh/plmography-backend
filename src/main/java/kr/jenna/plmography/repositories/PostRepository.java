package kr.jenna.plmography.repositories;

import kr.jenna.plmography.models.Post;
import kr.jenna.plmography.models.vo.UserId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    @Query("select p from Post p where p.userId = :userId and p.isDeleted = false")
    List<Post> findAllByUserIdAndIsDeleted(@Param("userId") UserId userId);

    Page<Post> findAll(Specification specification, Pageable pageable);

    List<Post> findAllByUserId(UserId userId);

    List<Post> findTop3ByOrderByHitDesc();
}
