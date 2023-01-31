package kr.jenna.plmography.repositories;

import kr.jenna.plmography.models.Subscribe;
import kr.jenna.plmography.models.vo.FollowingId;
import kr.jenna.plmography.models.vo.UserId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SubscribeRepository extends JpaRepository<Subscribe, Long> {
    @Modifying
    @Query("delete from Subscribe where userId = :userId and followingId = :followingId")
    void deleteAllByUserIdAndFollowingId(
            @Param("userId") UserId userId, @Param("followingId") FollowingId followingId);

    @Query("select s from Subscribe s where s.userId = :userId")
    Page<Subscribe> findAllByUserId(@Param("userId") UserId userId, Pageable pageable);

    @Query("select s from Subscribe s where s.followingId = :followingId")
    Page<Subscribe> findAllByFollowingId(@Param("followingId") FollowingId followingId, Pageable pageable);

    int countByUserId(UserId userId);

    int countByFollowingId(FollowingId followingId);

    @Query("select count(s.followingId) > 0 from Subscribe s where s.userId = :userId and s.followingId = :followingId")
    boolean existsByUserIdAndFollowingId(UserId userId, FollowingId followingId);
}
