package kr.jenna.plmography.repositories;

import kr.jenna.plmography.models.Content;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface ContentRepository extends JpaRepository<Content, Long> {

}
