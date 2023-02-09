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

    @Query("select p from Post p where p.isDeleted = false")
    Page<Post> findAllByIsDeleted(Specification specification, Pageable pageable);

    @Query("select p from Post p where p.isDeleted = false order by hit desc limit 6")
    List<Post> findTop6ByOrderByHitDesc();
}
