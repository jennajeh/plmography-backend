package kr.jenna.plmography.services.Recomment;

import kr.jenna.plmography.dtos.Recomment.RecommentRegistrationDto;
import kr.jenna.plmography.exceptions.UserNotFound;
import kr.jenna.plmography.models.Recomment;
import kr.jenna.plmography.models.User;
import kr.jenna.plmography.models.VO.CommentId;
import kr.jenna.plmography.models.VO.PostId;
import kr.jenna.plmography.models.VO.RecommentBody;
import kr.jenna.plmography.models.VO.UserId;
import kr.jenna.plmography.repositories.RecommentRepository;
import kr.jenna.plmography.repositories.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CreateRecommentService {
    private final RecommentRepository recommentRepository;
    private final UserRepository userRepository;

    public CreateRecommentService(RecommentRepository recommentRepository, UserRepository userRepository) {
        this.recommentRepository = recommentRepository;
        this.userRepository = userRepository;
    }

    public Recomment create(Long userId, RecommentRegistrationDto recommentRegistrationDto) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFound(userId));

        Recomment recomment = new Recomment(
                new CommentId(recommentRegistrationDto.getCommentId()),
                new RecommentBody(recommentRegistrationDto.getRecommentBody()),
                new UserId(recommentRegistrationDto.getUserId()),
                new PostId(recommentRegistrationDto.getPostId()));

        recommentRepository.save(recomment);

        return recomment;
    }
}
