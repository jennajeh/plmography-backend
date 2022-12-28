package kr.jenna.plmography.repositories;

import kr.jenna.plmography.models.Performer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PerformerRepository extends JpaRepository<Performer, Long> {
}
