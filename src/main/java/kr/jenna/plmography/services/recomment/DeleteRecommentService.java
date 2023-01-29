package kr.jenna.plmography.services.Recomment;

import kr.jenna.plmography.exceptions.InvalidUser;
import kr.jenna.plmography.exceptions.RecommentNotFound;
import kr.jenna.plmography.exceptions.UnmodifiableRecomment;
import kr.jenna.plmography.models.Recomment;
import kr.jenna.plmography.repositories.CommentRepository;
import kr.jenna.plmography.repositories.RecommentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class DeleteRecommentService {
    private final RecommentRepository recommentRepository;
    private final CommentRepository commentRepository;

    public DeleteRecommentService(RecommentRepository recommentRepository, CommentRepository commentRepository) {
        this.recommentRepository = recommentRepository;
        this.commentRepository = commentRepository;
    }

    public void delete(Long userId, Long recommentId) {
        Recomment recomment = recommentRepository.findById(recommentId)
                .orElseThrow(() -> new RecommentNotFound());

        List<Recomment> recomments = recommentRepository.findAllById(recommentId);

        if (!recomment.isWriter(userId)) {
            throw new InvalidUser();
        }

        if (recomments.size() > 1) {
            throw new UnmodifiableRecomment();
        }

        recommentRepository.delete(recomment);
    }
}
